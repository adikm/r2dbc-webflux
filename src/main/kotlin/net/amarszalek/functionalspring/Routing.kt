package net.amarszalek.functionalspring

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.*
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*

@Configuration
class Routing {


    @Bean
    fun booksRouter(handler: FileHandler) = router {
        ("/" and accept(MediaType.MULTIPART_FORM_DATA)).nest {
            POST("/import", handler::import)
            GET("/", handler::getAll)
        }
    }
}


