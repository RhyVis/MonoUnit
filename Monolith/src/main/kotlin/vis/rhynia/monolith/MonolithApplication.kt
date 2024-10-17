package vis.rhynia.monolith

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("vis.rhynia.monolith.core.config")
class MonolithApplication

fun main(args: Array<String>) {
    runApplication<MonolithApplication>(*args)
}

/** Logger Factory Extension */
val Any.log: Logger
    get() = LoggerFactory.getLogger(this::class.java)
