package vis.rhynia.monolith.core.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import vis.rhynia.monolith.core.element.entity.MemeDinner

interface MemeDinnerRepository : JpaRepository<MemeDinner, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM meme_dinner ORDER BY RAND() LIMIT 1")
    fun findRand(): MemeDinner?

    @Query(nativeQuery = true, value = "SELECT * FROM meme_dinner ORDER BY RAND() LIMIT ?1")
    fun findRand(limit: Int): List<MemeDinner>
}
