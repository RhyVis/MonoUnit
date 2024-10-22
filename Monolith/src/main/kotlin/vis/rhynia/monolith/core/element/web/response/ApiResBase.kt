package vis.rhynia.monolith.core.element.web.response

abstract class ApiResBase<T>(
    open var code: Int = 0,
    open var data: T,
) : IWebRes {
    constructor(data: T) : this(0, data)
}
