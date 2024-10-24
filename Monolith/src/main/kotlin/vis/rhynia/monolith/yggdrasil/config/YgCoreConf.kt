package vis.rhynia.monolith.yggdrasil.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("yggdrasil.core")
data class YgCoreConf(
    var rootUrl: String?,
)
