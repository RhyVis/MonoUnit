package vis.rhynia.monolith.core.service

import vis.rhynia.monolith.core.element.web.request.obj.TarotReq
import vis.rhynia.monolith.core.element.web.response.obj.AnyRes
import vis.rhynia.monolith.core.element.web.response.obj.SimpRes
import vis.rhynia.monolith.core.element.web.response.obj.TarotRes
import vis.rhynia.monolith.core.service.base.IServ

interface IServTarot : IServ {
    fun handleTarotDraw(): SimpRes

    fun handleTarotDraw(req: TarotReq): TarotRes

    fun handleTarotInfo(): AnyRes
}
