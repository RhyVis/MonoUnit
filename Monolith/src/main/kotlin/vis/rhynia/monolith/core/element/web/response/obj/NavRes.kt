package vis.rhynia.monolith.core.element.web.response.obj

import vis.rhynia.monolith.core.element.entity.StrNav
import vis.rhynia.monolith.core.element.web.response.ApiResBase

data class NavRes(
    override var data: List<StrNav>,
) : ApiResBase<List<StrNav>>(data)
