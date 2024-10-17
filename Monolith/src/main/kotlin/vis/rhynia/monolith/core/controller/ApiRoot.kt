package vis.rhynia.monolith.core.controller

import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vis.rhynia.monolith.log

@RestController
@RequestMapping("/api")
class ApiRoot : InitializingBean {
    @Value("\${spring.application.name}")
    private lateinit var appName: String

    private val metaMap = mutableMapOf<String, String>()

    override fun afterPropertiesSet() {
        metaMap["appName"] = appName
        log.debug("Meta initialized")
    }

    @RequestMapping
    fun meta() = metaMap
}
