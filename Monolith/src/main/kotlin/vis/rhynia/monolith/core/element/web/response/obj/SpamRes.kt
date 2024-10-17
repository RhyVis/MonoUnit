package vis.rhynia.monolith.core.element.web.response.obj

import vis.rhynia.monolith.core.element.web.response.ApiResBase

data class SpamRes(
    override var code: Int,
    override var data: List<SpamResData>,
) : ApiResBase<List<SpamRes.SpamResData>>(code, data) {
    data class SpamResData(
        val id: Long,
        var text: String,
    )
}
