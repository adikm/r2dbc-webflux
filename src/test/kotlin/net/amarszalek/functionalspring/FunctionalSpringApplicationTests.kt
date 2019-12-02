//package net.amarszalek.functionalspring
//
//import org.junit.jupiter.api.*
//import org.junit.jupiter.api.extension.ExtendWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.core.io.ClassPathResource
//import org.springframework.http.MediaType
//import org.springframework.http.client.MultipartBodyBuilder
//import org.springframework.test.context.ContextConfiguration
//import org.springframework.test.context.junit.jupiter.SpringExtension
//import org.springframework.test.web.reactive.server.WebTestClient
//import org.springframework.web.reactive.function.BodyInserters
//import org.springframework.context.ApplicationContext
//
//
//
//@WebFluxTest
//@ContextConfiguration(classes = [Routing::class, RequestHandler::class, CountryRepository::class])
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class FunctionalSpringApplicationTests {
//
//    @Autowired
//    private lateinit var context: ApplicationContext
//    @Autowired
//    private lateinit var client: WebTestClient
//    @BeforeAll
//    fun initClient() {
//        client = WebTestClient.bindToApplicationContext(context).build()
//    }
//
//    @Test
//    fun getBooks() {
//        val bodyBuilder = MultipartBodyBuilder()
//
//        bodyBuilder.part("profileImage", ClassPathResource("records.csv").file.readBytes()).header("Content-Disposition", "form-data; name=records; filename=records.csv")
//
//        client.post()
//                .uri("/import")
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
//                .exchange()
//                .expectStatus().isOk
//    }
//
//}
