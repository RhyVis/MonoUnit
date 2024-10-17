package vis.rhynia.monolith.core.element.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import vis.rhynia.monolith.core.element.entity.base.IEntrySpam

@Entity
@Table(name = "gacha_gs")
class GachaGs(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long,
    @Column(name = "text")
    override var text: String,
) : IEntrySpam
