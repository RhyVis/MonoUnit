package vis.rhynia.monolith

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import vis.rhynia.monolith.core.config.CoreConf
import vis.rhynia.monolith.yggdrasil.config.YgCoreConf
import vis.rhynia.monolith.yggdrasil.config.YgTextureConf

@SpringBootApplication
@EnableConfigurationProperties(CoreConf::class, YgCoreConf::class, YgTextureConf::class)
class MonolithApplication

fun main(args: Array<String>) {
    runApplication<MonolithApplication>(*args)
}

/** Logger Factory Extension */
val Any.log: Logger
    get() = LoggerFactory.getLogger(this::class.java)
