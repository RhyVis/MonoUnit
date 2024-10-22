package vis.rhynia.monolith.core.service.impl

import cn.hutool.http.HttpUtil
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder
import vis.rhynia.monolith.core.config.CoreConf
import vis.rhynia.monolith.core.element.data.mapping.ConfMapping
import vis.rhynia.monolith.core.element.data.tarot.TarotCardDrawn
import vis.rhynia.monolith.core.element.data.tarot.TarotDeck
import vis.rhynia.monolith.core.element.data.tarot.TarotDeckInfo
import vis.rhynia.monolith.core.element.web.request.obj.TarotReq
import vis.rhynia.monolith.core.element.web.response.obj.AnyRes
import vis.rhynia.monolith.core.element.web.response.obj.SimpRes
import vis.rhynia.monolith.core.element.web.response.obj.TarotRes
import vis.rhynia.monolith.core.service.IServTarot
import vis.rhynia.monolith.log
import kotlin.random.Random

@Service
class ServTarot(
    private val conf: CoreConf,
) : IServTarot,
    InitializingBean {
    private val decks = mutableMapOf<String, TarotDeck>()
    private val deckMainOnly = mutableMapOf<String, Boolean>()
    private val deckInfo = mutableMapOf<String, TarotDeckInfo>()

    override fun afterPropertiesSet() {
        val mainConfUri = buildUrl(conf.endpoint, "tarot", "conf", "_conf.json")
        val mainConf = Json.decodeFromString<ConfMapping>(HttpUtil.get(mainConfUri))
        for (mapping in mainConf.mappings) {
            val deckFileUri = buildUrl(conf.endpoint, "tarot", "conf", mapping.value)
            val deck = Json.decodeFromString<TarotDeck>(HttpUtil.get(deckFileUri))
            decks[mapping.key] = deck
            deckInfo[mapping.key] = deck.buildInfo()
            if (deck.full) {
                val altName = deck.name + "_main"
                val alt =
                    TarotDeck(
                        altName,
                        deck.loc,
                        false,
                        deck.hasR,
                        deck.deck.slice(IntRange(0, 21)),
                    )
                decks[altName] = alt
                deckMainOnly[mapping.key] = false
            } else {
                deckMainOnly[mapping.key] = true
            }
        }
        log.info("Processed ${decks.size} decks")
        decks.entries.forEach { log.debug("Deck - ${it.key}: ${it.value.deck.size}") }
    }

    override fun handleTarotDraw(): SimpRes = SimpRes(draw("waite", true, 1)[0].name)

    override fun handleTarotDraw(req: TarotReq): TarotRes {
        val (deck, full, size) = req.data
        return draw(deck, full, size).run {
            log.debug("Drawn {}", this.map { it.name })
            TarotRes(this)
        }
    }

    override fun handleTarotInfo(): AnyRes = AnyRes(deckInfo)

    fun draw(
        deck: String,
        full: Boolean,
        size: Int,
    ): List<TarotCardDrawn> =
        if (!deckMainOnly.containsKey(deck)) {
            emptyList()
        } else {
            if (deckMainOnly[deck] == true) {
                drawShuffled(deck, size)
            } else {
                if (full) {
                    drawShuffled(deck, size)
                } else {
                    drawShuffled(deck + "_main", size)
                }
            }
        }

    private fun drawShuffled(
        deck: String,
        count: Int,
    ): List<TarotCardDrawn> {
        val deckEntity = decks[deck] ?: return emptyList()
        val deckPicked = deckEntity.deck.shuffled().take(count)
        val deckImgPrefix = buildUrl(conf.endpoint, "tarot", "img")
        return if (deckEntity.hasR) {
            deckPicked.map { it.buildDrawn(deckImgPrefix, Random.nextBoolean()) }
        } else {
            deckPicked.map { it.buildDrawn(deckImgPrefix) }
        }
    }

    private fun buildUrl(
        endpoint: String,
        vararg paths: String,
    ) = UriComponentsBuilder
        .fromHttpUrl(endpoint)
        .pathSegment(*paths)
        .encode()
        .build()
        .toUriString()
}
