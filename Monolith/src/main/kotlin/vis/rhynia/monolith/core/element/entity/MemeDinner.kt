package vis.rhynia.monolith.core.element.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import vis.rhynia.monolith.core.element.entity.base.IEntrySpam

@Entity
@Table(name = "meme_dinner")
class MemeDinner(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long,
    @Column(name = "text")
    override var text: String,
) : IEntrySpam
