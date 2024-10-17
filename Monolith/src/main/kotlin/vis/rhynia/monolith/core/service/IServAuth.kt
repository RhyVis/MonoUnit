package vis.rhynia.monolith.core.service

import vis.rhynia.monolith.core.element.web.request.obj.AuthReq
import vis.rhynia.monolith.core.element.web.response.obj.AuthRes
import vis.rhynia.monolith.core.service.base.IServPost

interface IServAuth : IServPost<AuthReq, AuthRes> {
    override fun handlePostReq(req: AuthReq): AuthRes = handleAuthReq(req)

    fun handleAuthReq(req: AuthReq): AuthRes
}
