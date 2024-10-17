package vis.rhynia.monolith.core.service

import vis.rhynia.monolith.core.element.web.request.obj.DictReq
import vis.rhynia.monolith.core.element.web.response.obj.DictRes
import vis.rhynia.monolith.core.service.base.IServPost

interface IServDict : IServPost<DictReq, DictRes> {
    override fun handlePostReq(req: DictReq): DictRes = handleDictReq(req)

    fun handleDictReq(req: DictReq): DictRes
}
