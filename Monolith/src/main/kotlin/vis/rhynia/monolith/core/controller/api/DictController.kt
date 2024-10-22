package vis.rhynia.monolith.core.controller.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vis.rhynia.monolith.core.element.web.request.obj.DictReq
import vis.rhynia.monolith.core.element.web.response.obj.DictRes
import vis.rhynia.monolith.core.service.IServDict

@RestController
@RequestMapping("/api/codex")
class DictController(
    private val serv: IServDict,
) {
    @GetMapping
    fun get(): DictRes = DictRes(666, "玩抽象的这辈子有了")

    @PostMapping
    fun post(
        @RequestBody dictReq: DictReq,
    ): DictRes = serv.handlePostReq(dictReq)
}
