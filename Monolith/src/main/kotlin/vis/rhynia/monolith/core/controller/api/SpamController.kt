package vis.rhynia.monolith.core.controller.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vis.rhynia.monolith.core.element.web.request.obj.SpamReq
import vis.rhynia.monolith.core.element.web.response.obj.SimpRes
import vis.rhynia.monolith.core.service.IServSpam

@RestController
@RequestMapping("/api/spam")
class SpamController(
    private val serv: IServSpam,
) {
    @GetMapping
    fun get() = SimpRes(-1, "Use POST to fetch info")

    @PostMapping
    fun post(
        @RequestBody req: SpamReq,
    ) = serv.handleSpamReq(req)
}
