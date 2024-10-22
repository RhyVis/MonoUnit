package vis.rhynia.monolith.core.repository

import org.springframework.data.jpa.repository.JpaRepository
import vis.rhynia.monolith.core.element.entity.StrSave

interface StrSaveRepository : JpaRepository<StrSave, Long>
