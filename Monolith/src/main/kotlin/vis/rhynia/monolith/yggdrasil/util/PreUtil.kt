package vis.rhynia.monolith.yggdrasil.util

import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import vis.rhynia.monolith.yggdrasil.config.YgAccessor

@Component
class PreUtil(
    private val ya: YgAccessor,
) {
    fun rootUrlBuilder() = UriComponentsBuilder.fromHttpUrl(ya.rootUrl)
}
