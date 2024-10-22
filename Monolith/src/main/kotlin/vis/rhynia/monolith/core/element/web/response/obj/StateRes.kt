package vis.rhynia.monolith.core.element.web.response.obj

import vis.rhynia.monolith.core.element.web.response.ApiResBase

class StateRes(
    success: Boolean,
) : ApiResBase<Int>(if (success) 0 else -1, 0) {
    constructor() : this(true)
}
