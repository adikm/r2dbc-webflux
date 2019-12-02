package net.amarszalek.functionalspring

import org.springframework.http.codec.multipart.*
import org.springframework.stereotype.*
import org.springframework.web.reactive.function.*
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.*
import java.time.Duration

@Component
class RequestHandler(private val countryRepository: CountryRepository) {

    fun import(request: ServerRequest): Mono<ServerResponse> {
        return request.body(BodyExtractors.toMultipartData()).flatMap { parts ->
            val map: Map<String, Part> = parts.toSingleValueMap()
            (map["records"]!! as FilePart)
                    .content().map { dataBuffer ->
                        String(dataBuffer.asInputStream().readBytes()).lines().map {
                            val customeString = it.split(',')
                            when {
                                customeString.size > 1 -> {
                                    val country = customeString[1]
                                    val region = customeString[0]
                                    countryRepository.save(country, region)
                                }
                            }
                        }
                    }.subscribe()
            ServerResponse.ok().sse().body<Country>(countryRepository.getAll())
        }
    }

    fun getAll(request: ServerRequest): Mono<ServerResponse> {
        val countryEverySecond = countryRepository.getAll()
                .zipWith(Flux.interval(Duration.ofSeconds(1)))
        return ServerResponse.ok().sse().body<Country>(countryEverySecond.map { it.t1 })
    }

    fun getOne(request: ServerRequest): Mono<ServerResponse> {
        val name = request.pathVariable("name")
        val country = countryRepository.getOne(name)
        return ServerResponse.ok().body<Country>(country)
    }
}
