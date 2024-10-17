package vis.rhynia.monolith.core.element.web.request

abstract class ApiReqBase<T>(
    open val code: Int,
    open val meta: String,
    open val data: T,
) : IWebReq
