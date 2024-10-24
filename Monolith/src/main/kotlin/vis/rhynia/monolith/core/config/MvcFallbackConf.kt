package vis.rhynia.monolith.core.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.http.CacheControl
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.mvc.WebContentInterceptor
import org.springframework.web.servlet.resource.PathResourceResolver

@Configuration
class MvcFallbackConf(
    private val webProperties: WebProperties,
    @Value("\${user.dir}") private val rootDir: String,
) : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry
            .addResourceHandler("/**")
            .addResourceLocations(*webProperties.resources.staticLocations)
            .addResourceLocations("file:$rootDir/static/")
            .resourceChain(true)
            .addResolver(
                object : PathResourceResolver() {
                    override fun getResource(
                        resourcePath: String,
                        location: Resource,
                    ): Resource? =
                        super.getResource(resourcePath, location)
                            ?: super.getResource("index.html", location)
                },
            )
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        WebContentInterceptor().apply {
            addCacheMapping(CacheControl.noCache(), "index.html")
            addCacheMapping(CacheControl.noStore(), "index.html")
            registry.addInterceptor(this)
        }
    }
}
