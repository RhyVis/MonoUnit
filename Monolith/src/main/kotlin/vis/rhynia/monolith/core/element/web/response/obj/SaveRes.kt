package vis.rhynia.monolith.core.element.web.response.obj

import vis.rhynia.monolith.core.element.web.response.ApiResBase

data class SaveRes(
    override var code: Int,
    override var data: SaveResData,
) : ApiResBase<SaveRes.SaveResData>(code, data) {
    constructor(data: SaveResData) : this(0, data)

    data class SaveResData(
        var id: Long,
        var text: String,
        var note: String,
    )

    companion object {
        fun fail() = SaveRes(-1, SaveResData(-1, "FAIL", "FAIL"))
    }
}
