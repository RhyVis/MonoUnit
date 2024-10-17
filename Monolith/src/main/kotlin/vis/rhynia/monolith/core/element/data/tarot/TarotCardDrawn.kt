package vis.rhynia.monolith.core.element.data.tarot

data class TarotCardDrawn(
    val index: Int,
    val name: String,
    val loc: String,
    var img: String,
    var rev: Boolean,
    val desc: TarotCard.TarotDesc,
)
