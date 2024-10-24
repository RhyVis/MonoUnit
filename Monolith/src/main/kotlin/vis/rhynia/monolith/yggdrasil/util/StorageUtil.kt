package vis.rhynia.monolith.yggdrasil.util

import cn.hutool.core.io.FileUtil
import org.springframework.stereotype.Component
import vis.rhynia.monolith.yggdrasil.config.YgAccessor

@Component
class StorageUtil(
    private val ya: YgAccessor,
) {
    fun saveTexture(
        skin: Boolean,
        sha: String,
        c: ByteArray,
    ) {
        FileUtil.writeBytes(
            c,
            ya.texturePath
                .resolve(if (skin) "skin" else "cape")
                .resolve(sha)
                .toFile(),
        )
    }
}
