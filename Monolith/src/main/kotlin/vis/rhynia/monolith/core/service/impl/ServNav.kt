package vis.rhynia.monolith.core.service.impl

import org.springframework.stereotype.Component
import vis.rhynia.monolith.core.element.entity.StrNav
import vis.rhynia.monolith.core.element.web.response.obj.NavRes
import vis.rhynia.monolith.core.repository.StrNavRepository
import vis.rhynia.monolith.core.service.IServNav

@Component
class ServNav(
    private val repo: StrNavRepository,
) : IServNav {
    override fun handleNavReq(): NavRes = NavRes(getIndexListData())

    private fun getIndexListData(): List<StrNav> = repo.findAll()
}
