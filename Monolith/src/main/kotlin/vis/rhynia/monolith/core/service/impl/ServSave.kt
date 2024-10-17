package vis.rhynia.monolith.core.service.impl

import org.springframework.stereotype.Service
import vis.rhynia.monolith.core.element.entity.StrSave
import vis.rhynia.monolith.core.element.web.request.obj.SaveReq
import vis.rhynia.monolith.core.element.web.response.obj.SaveRes
import vis.rhynia.monolith.core.element.web.response.obj.StateRes
import vis.rhynia.monolith.core.repository.StrSaveRepository
import vis.rhynia.monolith.core.service.IServSave

@Service
class ServSave(
    private val repo: StrSaveRepository,
) : IServSave {
    override fun handleQuery(req: SaveReq) = queryStr(req.data.id)

    override fun handleUpdate(req: SaveReq): StateRes =
        req.let {
            val (id, text, note) = req.data
            updateStr(id, text, note)
            StateRes()
        }

    fun queryStr(id: Long): SaveRes =
        repo.findById(id).let {
            if (it.isPresent) it.get().toRes() else SaveRes.fail()
        }

    fun updateStr(
        id: Long,
        text: String,
        note: String = "",
    ) {
        repo.findById(id).ifPresentOrElse(
            {
                it.text = text
                it.note = note
                repo.save(it)
            },
            { repo.save(StrSave(id, text, note)) },
        )
    }

    fun deleteStr(id: Long) {
        repo.deleteById(id)
    }
}
