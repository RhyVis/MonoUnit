package vis.rhynia.monolith.yggdrasil.element

import org.springframework.stereotype.Component
import vis.rhynia.monolith.yggdrasil.util.PreUtil
import java.awt.image.BufferedImage
import java.math.BigInteger
import java.security.MessageDigest

class Texture(
    val hash: String,
    val data: ByteArray,
    val url: String,
) {
    companion object {
        fun computeHash(img: BufferedImage): String {
            val digest = MessageDigest.getInstance("SHA-256")
            val width = img.width
            val height = img.height

            ByteArray(4096).run {
                this.putInt(0, width)
                this.putInt(4, height)
                var pos = 8

                for (x in 0 until width) {
                    for (y in 0 until height) {
                        this.putInt(pos, img.getRGB(x, y))
                        if (this[pos] == 0.toByte()) {
                            this[pos + 3] = 0.toByte()
                            this[pos + 2] = 0.toByte()
                            this[pos + 1] = 0.toByte()
                        }
                        pos += 4
                        if (pos == this.size) {
                            pos = 0
                            digest.update(this, 0, this.size)
                        }
                    }
                }

                if (pos > 0) {
                    digest.update(this, 0, pos)
                }

                digest.digest().run {
                    return "${BigInteger(1,this)}${this.size shl 1}x"
                }
            }
        }
    }

    @Component
    class Storage(
        private val preUtil: PreUtil,
    )
}

private fun ByteArray.putInt(
    offset: Int,
    x: Int,
) {
    this[offset + 0] = (x shr 24 and 0xff).toByte()
    this[offset + 1] = (x shr 16 and 0xff).toByte()
    this[offset + 2] = (x shr 8 and 0xff).toByte()
    this[offset + 3] = (x shr 0 and 0xff).toByte()
}
