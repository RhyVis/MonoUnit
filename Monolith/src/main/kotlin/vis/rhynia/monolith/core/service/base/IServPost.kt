package vis.rhynia.monolith.core.service.base

import vis.rhynia.monolith.core.element.web.request.IWebReq
import vis.rhynia.monolith.core.element.web.response.IWebRes

interface IServPost<in I : IWebReq, out O : IWebRes> : IServ {
    fun handlePostReq(req: I): O
}
