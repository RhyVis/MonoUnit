package vis.rhynia.monolith.core.element.web.request.obj

import vis.rhynia.monolith.core.element.web.request.ApiReqBase

data class TarotReq(
    override val code: Int,
    override val meta: String,
    override val data: TarotReqData,
) : ApiReqBase<TarotReq.TarotReqData>(code, meta, data) {
    data class TarotReqData(
        val deck: String,
        val full: Boolean,
        val size: Int,
    )
}
