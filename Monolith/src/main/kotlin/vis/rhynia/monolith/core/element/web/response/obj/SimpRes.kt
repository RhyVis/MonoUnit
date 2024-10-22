package vis.rhynia.monolith.core.element.web.response.obj

import vis.rhynia.monolith.core.element.web.response.ApiResBase

data class SimpRes(
    override var code: Int,
    override var data: String,
) : ApiResBase<String>(code, data) {
    constructor(data: String) : this(0, data)
}
