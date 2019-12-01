package net.amarszalek.functionalspring

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.*
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.util.stream.Stream


@Component
class ApplicationConfiguration {

    @Bean
    fun runner(employeeRepository: CustomerRepository, db: DatabaseClient) = ApplicationRunner {
        val initDb = db.execute {
            """ CREATE TABLE country (
                    name VARCHAR(255) NOT NULL,
                    continent VARCHAR(255) NOT NULL
                );
            """
        }

        initDb // initialize the database
                .then()
                .subscribe() // execute
    }
}
