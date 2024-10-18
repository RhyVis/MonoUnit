package vis.rhynia.monolith.core.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "monolith")
data class CoreConf(
    /** Web static resource endpoint */
    var endpoint: String = "/",
    /** Token string */
    var token: String = "",
)
