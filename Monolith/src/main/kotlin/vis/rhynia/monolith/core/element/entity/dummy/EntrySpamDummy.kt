package vis.rhynia.monolith.core.element.entity.dummy

import vis.rhynia.monolith.core.element.entity.base.IEntrySpam

data class EntrySpamDummy(
    override val id: Long = -1,
    override var text: String = "Dummy",
) : IEntrySpam
