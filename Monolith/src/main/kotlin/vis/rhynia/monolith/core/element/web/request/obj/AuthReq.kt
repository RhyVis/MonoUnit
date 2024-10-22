package vis.rhynia.monolith.core.element.web.request.obj

import vis.rhynia.monolith.core.element.web.request.ApiReqBase

data class AuthReq(
    override val code: Int,
    override val meta: String,
    override val data: String,
) : ApiReqBase<String>(code, meta, data)
