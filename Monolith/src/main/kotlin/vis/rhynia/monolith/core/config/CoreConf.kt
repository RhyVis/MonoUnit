package vis.rhynia.monolith.core.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "monolith")
data class CoreConf(
    var endpoint: String = "/",
    var token: String = "",
)
