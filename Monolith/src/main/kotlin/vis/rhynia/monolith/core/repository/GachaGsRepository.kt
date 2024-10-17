package vis.rhynia.monolith.core.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import vis.rhynia.monolith.core.element.entity.GachaGs

interface GachaGsRepository : JpaRepository<GachaGs, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM gacha_gs ORDER BY RAND() LIMIT 1")
    fun findRand(): GachaGs?

    @Query(nativeQuery = true, value = "SELECT * FROM gacha_gs ORDER BY RAND() LIMIT ?1")
    fun findRand(limit: Int): List<GachaGs>
}
