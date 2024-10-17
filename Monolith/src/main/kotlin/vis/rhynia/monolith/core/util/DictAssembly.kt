package vis.rhynia.monolith.core.util

import cn.hutool.core.io.resource.ResourceUtil
import kotlinx.serialization.json.Json
import vis.rhynia.monolith.log

object DictAssembly {
    val DICT_HAN_EMOJI =
        ResourceUtil
            .readUtf8Str("conf/codex/dict_han_emoji.json")
            .run { jsonToArrayMap(this) }
            .also { log.debug("DICT_HAN_EMOJI ${it.size}") }
    val DICT_EMOJI_HAN =
        ResourceUtil
            .readUtf8Str("conf/codex/dict_emoji_han.json")
            .run { jsonToStringMap(this) }
            .also { log.debug("DICT_EMOJI_HAN ${it.size}") }
    val DICT_HAN_SPARK =
        ResourceUtil
            .readUtf8Str("conf/codex/dict_han_spark.json")
            .run { jsonToStringMap(this) }
            .also { log.debug("DICT_HAN_SPARK ${it.size}") }
    val DICT_SPARK_HAN =
        ResourceUtil
            .readUtf8Str("conf/codex/dict_spark_han.json")
            .run { jsonToStringMap(this) }
            .also { log.debug("DICT_SPARK_HAN ${it.size}") }
    val DICT_PIN_EMOJI =
        ResourceUtil
            .readUtf8Str("conf/codex/dict_py_emoji.json")
            .run { jsonToStringMap(this) }
            .also { log.debug("DICT_PIN_EMOJI ${it.size}") }
    val DICT_UNICODE_D =
        ResourceUtil
            .readUtf8Str("conf/codex/unicode_diff.json")
            .run { jsonToStringMap(this) }
            .also { log.debug("DICT_UNICODE_D ${it.size}") }

    fun init() = Unit

    private fun jsonToStringMap(json: String): Map<String, String> = Json.decodeFromString<Map<String, String>>(json)

    private fun jsonToArrayMap(json: String): Map<String, Array<String>> = Json.decodeFromString<Map<String, Array<String>>>(json)
}
