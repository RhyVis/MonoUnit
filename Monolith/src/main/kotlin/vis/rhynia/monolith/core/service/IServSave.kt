package vis.rhynia.monolith.core.service

import vis.rhynia.monolith.core.element.web.request.obj.SaveReq
import vis.rhynia.monolith.core.element.web.response.obj.SaveRes
import vis.rhynia.monolith.core.element.web.response.obj.StateRes
import vis.rhynia.monolith.core.service.base.IServ

interface IServSave : IServ {
    fun handleQuery(req: SaveReq): SaveRes

    fun handleUpdate(req: SaveReq): StateRes
}
