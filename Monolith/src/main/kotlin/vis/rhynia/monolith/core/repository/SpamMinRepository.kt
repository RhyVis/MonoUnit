package vis.rhynia.monolith.core.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import vis.rhynia.monolith.core.element.entity.SpamMin

interface SpamMinRepository : JpaRepository<SpamMin, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM spam_min ORDER BY RAND() LIMIT 1")
    fun findRand(): SpamMin?

    @Query(nativeQuery = true, value = "SELECT * FROM spam_min ORDER BY RAND() LIMIT ?1")
    fun findRand(limit: Int): List<SpamMin>
}
