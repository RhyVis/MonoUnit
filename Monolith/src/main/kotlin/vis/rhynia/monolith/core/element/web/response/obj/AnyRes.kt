package vis.rhynia.monolith.core.element.web.response.obj

import vis.rhynia.monolith.core.element.web.response.ApiResBase

data class AnyRes(
    override var code: Int,
    override var data: Any,
) : ApiResBase<Any>(code, data) {
    constructor(data: Any) : this(0, data)
}
