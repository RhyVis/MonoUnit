package vis.rhynia.monolith.yggdrasil.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.file.Path

@Component
class YgDefault(
    @Value("\${user.dir}") private val rootDir: Path,
) {
    val defaultTexturePath: Path = rootDir.resolve("textures")
}
