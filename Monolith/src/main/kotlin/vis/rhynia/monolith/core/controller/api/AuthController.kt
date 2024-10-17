package vis.rhynia.monolith.core.controller.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vis.rhynia.monolith.core.element.web.request.obj.AuthReq
import vis.rhynia.monolith.core.element.web.response.obj.AuthRes
import vis.rhynia.monolith.core.service.IServAuth

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val serv: IServAuth,
) {
    @PostMapping
    fun post(
        @RequestBody authReq: AuthReq,
    ): AuthRes = serv.handlePostReq(authReq)
}
