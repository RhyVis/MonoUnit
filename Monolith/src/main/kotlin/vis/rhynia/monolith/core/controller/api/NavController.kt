package vis.rhynia.monolith.core.controller.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vis.rhynia.monolith.core.service.IServNav

@RestController
@RequestMapping("/api/nav")
class NavController(
    private val serv: IServNav,
) {
    @GetMapping
    fun get() = serv.handleGetReq()
}
