package vis.rhynia.monolith.core.element.entity.base

import vis.rhynia.monolith.core.element.web.response.obj.SpamRes

interface IEntrySpam : IEntryText {
    fun toSpamResData() = SpamRes.SpamResData(id, text)
}
