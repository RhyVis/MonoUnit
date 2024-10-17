package vis.rhynia.monolith.core.controller.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vis.rhynia.monolith.core.element.web.request.obj.SaveReq
import vis.rhynia.monolith.core.service.IServSave

@RestController
@RequestMapping("/api/save")
class SaveController(
    private val serv: IServSave,
) {
    @PostMapping("/query")
    fun query(
        @RequestBody req: SaveReq,
    ) = serv.handleQuery(req)

    @PostMapping("/update")
    fun update(
        @RequestBody req: SaveReq,
    ) = serv.handleUpdate(req)
}
