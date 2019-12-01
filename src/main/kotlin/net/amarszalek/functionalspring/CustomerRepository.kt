package net.amarszalek.functionalspring

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.query.Criteria
import org.springframework.stereotype.Repository
import reactor.core.publisher.*


@Repository
class CustomerRepository(private val databaseClient: DatabaseClient) {

    fun getAll(): Flux<Country> {
        return databaseClient
                .select().from("country")
                .`as`(Country::class.java)
                .fetch().all()
                .switchIfEmpty(Mono.error(RuntimeException("No countries ")))
    }

    fun getOne(name: String): Mono<Country> {
        return databaseClient
                .select().from("country")
                .matching(Criteria.where("name").`is`(name))
                .`as`(Country::class.java)
                .fetch().one()
                .switchIfEmpty(Mono.error(RuntimeException("No country")))
    }


    fun save(name: String, continent: String) {
        databaseClient
                .insert()
                .into(Country::class.java)
                .table("country")
                .using(Country(name, continent))
                .then().subscribe()
    }

}
