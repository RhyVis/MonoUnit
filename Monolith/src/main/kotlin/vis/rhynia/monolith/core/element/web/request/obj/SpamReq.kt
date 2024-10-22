package vis.rhynia.monolith.core.element.web.request.obj

import vis.rhynia.monolith.core.element.web.request.ApiReqBase

data class SpamReq(
    override val code: Int,
    override val meta: String,
    override val data: SpamReqData,
) : ApiReqBase<SpamReq.SpamReqData>(code, meta, data) {
    data class SpamReqData(
        val type: String,
        val dict: String,
        val size: Int,
    )
}
