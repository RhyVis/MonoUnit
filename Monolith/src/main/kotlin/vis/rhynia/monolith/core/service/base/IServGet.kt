package vis.rhynia.monolith.core.service.base

import vis.rhynia.monolith.core.element.web.response.IWebRes

interface IServGet<out O : IWebRes> : IServ {
    fun handleGetReq(): O
}
