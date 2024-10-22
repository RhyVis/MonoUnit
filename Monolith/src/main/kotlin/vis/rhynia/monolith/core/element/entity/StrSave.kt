package vis.rhynia.monolith.core.element.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import vis.rhynia.monolith.core.element.web.response.obj.SaveRes

@Entity
@Table(name = "str_save")
class StrSave(
    @Id
    val id: Long,
    @Column(name = "text")
    var text: String,
    @Column(name = "note")
    var note: String,
) {
    fun toRes() = SaveRes(SaveRes.SaveResData(id, text, note))
}
