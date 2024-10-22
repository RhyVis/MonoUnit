package vis.rhynia.monolith.core.service.impl

import org.springframework.stereotype.Service
import vis.rhynia.monolith.core.element.entity.GachaAk
import vis.rhynia.monolith.core.element.entity.GachaGs
import vis.rhynia.monolith.core.element.entity.MemeAcgn
import vis.rhynia.monolith.core.element.entity.MemeDinner
import vis.rhynia.monolith.core.element.entity.SpamMax
import vis.rhynia.monolith.core.element.entity.SpamMin
import vis.rhynia.monolith.core.element.entity.dummy.EntrySpamDummy
import vis.rhynia.monolith.core.element.type.SpamType
import vis.rhynia.monolith.core.element.type.SpamType.ACGN
import vis.rhynia.monolith.core.element.type.SpamType.ARKNIGHTS
import vis.rhynia.monolith.core.element.type.SpamType.DINNER
import vis.rhynia.monolith.core.element.type.SpamType.GENSHIN
import vis.rhynia.monolith.core.element.type.SpamType.SPAM_MAX
import vis.rhynia.monolith.core.element.type.SpamType.SPAM_MIN
import vis.rhynia.monolith.core.element.web.request.obj.SpamReq
import vis.rhynia.monolith.core.element.web.response.obj.SpamRes
import vis.rhynia.monolith.core.repository.GachaAkRepository
import vis.rhynia.monolith.core.repository.GachaGsRepository
import vis.rhynia.monolith.core.repository.MemeAcgnRepository
import vis.rhynia.monolith.core.repository.MemeDinnerRepository
import vis.rhynia.monolith.core.repository.SpamMaxRepository
import vis.rhynia.monolith.core.repository.SpamMinRepository
import vis.rhynia.monolith.core.service.IServSpam

@Service
class ServSpam(
    private val repoAk: GachaAkRepository,
    private val repoGs: GachaGsRepository,
    private val repoAc: MemeAcgnRepository,
    private val repoDi: MemeDinnerRepository,
    private val repoMx: SpamMaxRepository,
    private val repoMn: SpamMinRepository,
    private val servDict: ServDict,
) : IServSpam {
    override fun handleSpamReq(req: SpamReq): SpamRes {
        val (type, dict, size) = req.data
        val t = SpamType.tryGetValue(type.ifBlank { "none" }.uppercase())
        return if (size > 1) {
            when (t) {
                ARKNIGHTS -> fetchAk(size)
                GENSHIN -> fetchGs(size)
                SPAM_MIN -> fetchMn(size)
                SPAM_MAX -> fetchMx(size)
                ACGN -> fetchAc(size)
                DINNER -> fetchDi(size)
                else -> listOf(EntrySpamDummy())
            }.map {
                it.toSpamResData().apply {
                    text = servDict.dict(dict, text, true)
                }
            }.let { SpamRes(0, it) }
        } else {
            listOf(
                when (t) {
                    ARKNIGHTS -> fetchAk()
                    GENSHIN -> fetchGs()
                    SPAM_MIN -> fetchMn()
                    SPAM_MAX -> fetchMx()
                    ACGN -> fetchAc()
                    DINNER -> fetchDi()
                    else -> EntrySpamDummy()
                }.run {
                    text = servDict.dict(dict, text, true)
                    this.toSpamResData()
                },
            ).let { SpamRes(0, it) }
        }
    }

    private fun fetchAk() = repoAk.findRand() ?: GachaAk(-1, "Empty")

    private fun fetchGs() = repoGs.findRand() ?: GachaGs(-1, "Empty")

    private fun fetchMn() = repoMn.findRand() ?: SpamMin(-1, "Empty")

    private fun fetchMx() = repoMx.findRand() ?: SpamMax(-1, "Empty")

    private fun fetchAc() = repoAc.findRand() ?: MemeAcgn(-1, "Empty")

    private fun fetchDi() = repoDi.findRand() ?: MemeDinner(-1, "Empty")

    private fun fetchAk(size: Int) = repoAk.findRand(size)

    private fun fetchGs(size: Int) = repoGs.findRand(size)

    private fun fetchMn(size: Int) = repoMn.findRand(size)

    private fun fetchMx(size: Int) = repoMx.findRand(size)

    private fun fetchAc(size: Int) = repoAc.findRand(size)

    private fun fetchDi(size: Int) = repoDi.findRand(size)
}
