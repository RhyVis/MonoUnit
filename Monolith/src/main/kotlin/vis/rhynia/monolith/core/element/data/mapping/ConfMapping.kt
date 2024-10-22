package vis.rhynia.monolith.core.element.data.mapping

import kotlinx.serialization.Serializable

@Serializable
data class ConfMapping(
    val mappings: Map<String, String>,
)
