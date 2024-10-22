package vis.rhynia.monolith.core.element.web.request.obj

import vis.rhynia.monolith.core.element.web.request.ApiReqBase

data class DictReq(
    override val code: Int,
    override val meta: String,
    override val data: CodexReqData,
) : ApiReqBase<DictReq.CodexReqData>(code, meta, data) {
    data class CodexReqData(
        val type: String,
        val text: String,
        val encode: Boolean,
    )
}
