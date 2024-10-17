package vis.rhynia.monolith.core.element.web.response.obj

import vis.rhynia.monolith.core.element.web.response.ApiResBase

data class AuthRes(
    override var data: Boolean,
) : ApiResBase<Boolean>(data)
