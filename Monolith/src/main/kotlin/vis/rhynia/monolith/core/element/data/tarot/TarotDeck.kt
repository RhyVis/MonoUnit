package vis.rhynia.monolith.core.element.data.tarot

import kotlinx.serialization.Serializable

@Serializable
data class TarotDeck(
    val name: String,
    val loc: String,
    val full: Boolean,
    val hasR: Boolean,
    val deck: List<TarotCard>,
) {
    fun buildInfo(): TarotDeckInfo =
        TarotDeckInfo(
            name,
            loc,
            full,
        )
}
