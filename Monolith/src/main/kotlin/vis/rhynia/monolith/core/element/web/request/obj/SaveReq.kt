package vis.rhynia.monolith.core.element.web.request.obj

import vis.rhynia.monolith.core.element.web.request.ApiReqBase

data class SaveReq(
    override val code: Int,
    override val meta: String,
    override val data: SaveReqData,
) : ApiReqBase<SaveReq.SaveReqData>(code, meta, data) {
    data class SaveReqData(
        val id: Long,
        val text: String,
        val note: String,
    )
}
