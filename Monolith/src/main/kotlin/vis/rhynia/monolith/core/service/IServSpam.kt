package vis.rhynia.monolith.core.service

import vis.rhynia.monolith.core.element.web.request.obj.SpamReq
import vis.rhynia.monolith.core.element.web.response.obj.SpamRes
import vis.rhynia.monolith.core.service.base.IServPost

interface IServSpam : IServPost<SpamReq, SpamRes> {
    override fun handlePostReq(req: SpamReq): SpamRes = handleSpamReq(req)

    fun handleSpamReq(req: SpamReq): SpamRes
}
