package vis.rhynia.monolith.core.service.impl

import com.github.houbb.opencc4j.util.ZhConverterUtil
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import vis.rhynia.monolith.core.element.type.CodexType
import vis.rhynia.monolith.core.element.type.CodexType.DIFF
import vis.rhynia.monolith.core.element.type.CodexType.NMSL
import vis.rhynia.monolith.core.element.type.CodexType.SPRK
import vis.rhynia.monolith.core.element.type.CodexType.TRAD
import vis.rhynia.monolith.core.element.web.request.obj.DictReq
import vis.rhynia.monolith.core.element.web.response.obj.DictRes
import vis.rhynia.monolith.core.service.IServDict
import vis.rhynia.monolith.core.util.DictAssembly
import vis.rhynia.monolith.core.util.DictAssembly.DICT_EMOJI_HAN
import vis.rhynia.monolith.core.util.DictAssembly.DICT_HAN_EMOJI
import vis.rhynia.monolith.core.util.DictAssembly.DICT_HAN_SPARK
import vis.rhynia.monolith.core.util.DictAssembly.DICT_SPARK_HAN
import vis.rhynia.monolith.core.util.DictAssembly.DICT_UNICODE_D
import kotlin.random.Random

@Component
class ServDict :
    IServDict,
    InitializingBean {
    override fun afterPropertiesSet() {
        DictAssembly.init()
    }

    override fun handleDictReq(req: DictReq): DictRes {
        val (type, text, encode) = req.data
        return DictRes(dict(type, text, encode))
    }

    fun dict(
        type: String,
        text: String,
        encode: Boolean,
    ): String {
        val t = CodexType.tryGetValue(type.ifBlank { "none" }.uppercase())
        return when (t) {
            NMSL -> if (encode) encodeNmsl(text) else decodeNmsl(text)
            TRAD -> if (encode) encodeTrad(text) else decodeTrad(text)
            SPRK -> if (encode) encodeSprk(text) else decodeSprk(text)
            DIFF -> encodeDiff(text)
            else -> text
        }
    }

    companion object {
        private fun encodeNmsl(text: String): String = replaceRefArrMap(text, DICT_HAN_EMOJI)

        private fun decodeNmsl(text: String): String = replaceRefMap(text, DICT_EMOJI_HAN)

        private fun encodeSprk(text: String): String = replaceRefMap(text, DICT_HAN_SPARK)

        private fun decodeSprk(text: String): String = replaceRefMap(text, DICT_SPARK_HAN)

        private fun encodeDiff(text: String): String = replaceRefMap(text, DICT_UNICODE_D)

        private fun encodeTrad(text: String): String = ZhConverterUtil.toTraditional(text)

        private fun decodeTrad(text: String): String = ZhConverterUtil.toSimple(text)

        private fun replaceRefMap(
            text: String,
            ref: Map<String, String>,
        ): String = text.map { ref[it.toString()] ?: it }.joinToString("")

        private fun replaceRefArrMap(
            text: String,
            ref: Map<String, Array<String>>,
        ): String =
            text
                .map {
                    ref[it.toString()]?.run { if (size > 1) this[Random.nextInt(size)] else this[0] } ?: it
                }.joinToString("")
    }
}
