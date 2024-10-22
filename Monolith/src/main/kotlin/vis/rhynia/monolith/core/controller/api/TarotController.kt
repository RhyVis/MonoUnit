package vis.rhynia.monolith.core.controller.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vis.rhynia.monolith.core.element.web.request.obj.TarotReq
import vis.rhynia.monolith.core.service.IServTarot

@RestController
@RequestMapping("/api/tarot")
class TarotController(
    private val serv: IServTarot,
) {
    @GetMapping
    fun get() = serv.handleTarotDraw()

    @PostMapping
    fun post(
        @RequestBody req: TarotReq,
    ) = serv.handleTarotDraw(req)

    @GetMapping("/info")
    fun getInfo() = serv.handleTarotInfo()
}
