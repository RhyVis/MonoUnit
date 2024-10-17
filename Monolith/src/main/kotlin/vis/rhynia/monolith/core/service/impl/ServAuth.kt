package vis.rhynia.monolith.core.service.impl

import cn.hutool.core.util.RandomUtil
import cn.hutool.crypto.SecureUtil
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import vis.rhynia.monolith.core.config.CoreConf
import vis.rhynia.monolith.core.element.web.request.obj.AuthReq
import vis.rhynia.monolith.core.element.web.response.obj.AuthRes
import vis.rhynia.monolith.core.service.IServAuth
import vis.rhynia.monolith.log

@Component
class ServAuth(
    private val conf: CoreConf,
) : IServAuth,
    InitializingBean {
    private var tokenHash = ""

    override fun afterPropertiesSet() {
        conf.token
            .let {
                it.ifEmpty {
                    RandomUtil.randomString(32).also { rand -> log.warn("Token not set, giving random of $rand") }
                }
            }.let { tokenHash = SecureUtil.sha256(it) }
    }

    override fun handleAuthReq(req: AuthReq): AuthRes =
        (req.data == tokenHash)
            .also { log.info("Hash challenged: ${req.data}; result: $it") }
            .run { AuthRes(this) }
}
