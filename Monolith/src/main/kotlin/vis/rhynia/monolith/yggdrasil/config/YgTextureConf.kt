package vis.rhynia.monolith.yggdrasil.config

import org.springframework.boot.context.properties.ConfigurationProperties
import java.nio.file.Path

@ConfigurationProperties("yggdrasil.texture")
data class YgTextureConf(
    var texturePath: Path?,
)
