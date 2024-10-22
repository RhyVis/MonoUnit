package vis.rhynia.monolith.core.element.type

import vis.rhynia.monolith.log

enum class CodexType {
    NONE,
    NMSL,
    TRAD,
    SPRK,
    DIFF,
    NARY, ;

    companion object {
        fun tryGetValue(name: String): CodexType =
            try {
                valueOf(name)
            } catch (e: Exception) {
                log.error("Unrecognized type of $name", e)
                NONE
            }
    }
}
