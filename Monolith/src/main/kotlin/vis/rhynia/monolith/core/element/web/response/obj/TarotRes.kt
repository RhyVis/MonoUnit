package vis.rhynia.monolith.core.element.web.response.obj

import vis.rhynia.monolith.core.element.data.tarot.TarotCardDrawn
import vis.rhynia.monolith.core.element.web.response.ApiResBase

data class TarotRes(
    override var code: Int,
    override var data: List<TarotCardDrawn>,
) : ApiResBase<List<TarotCardDrawn>>(code, data) {
    constructor(data: List<TarotCardDrawn>) : this(0, data)
}
