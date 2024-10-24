package vis.rhynia.monolith.yggdrasil.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("yggdrasil")
data class YggdrasilConf(
    var rootUrl: String = "",
)
