package net.amarszalek.functionalspring

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.*
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component


@Component
class AppConfig {

    @Bean
    fun init(employeeRepository: CountryRepository, db: DatabaseClient) = ApplicationRunner {
        db.execute {
            """ CREATE TABLE country (
                    name VARCHAR(255) NOT NULL,
                    continent VARCHAR(255) NOT NULL
                );
            """
        }.then().subscribe()

    }
}
