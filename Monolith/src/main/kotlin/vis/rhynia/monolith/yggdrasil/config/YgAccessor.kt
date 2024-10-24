package vis.rhynia.monolith.yggdrasil.config

import org.springframework.stereotype.Component
import java.nio.file.Path

@Component
class YgAccessor(
    private val ygCoreConf: YgCoreConf,
    private val ygTextureConf: YgTextureConf,
    private val ygDefault: YgDefault,
) {
    val rootUrl: String
        get() = ygCoreConf.rootUrl ?: ""

    val texturePath: Path
        get() = ygTextureConf.texturePath ?: ygDefault.defaultTexturePath
}
