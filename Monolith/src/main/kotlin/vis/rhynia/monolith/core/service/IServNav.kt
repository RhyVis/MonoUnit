package vis.rhynia.monolith.core.service

import vis.rhynia.monolith.core.element.web.response.obj.NavRes
import vis.rhynia.monolith.core.service.base.IServGet

interface IServNav : IServGet<NavRes> {
    override fun handleGetReq(): NavRes = handleNavReq()

    fun handleNavReq(): NavRes
}
