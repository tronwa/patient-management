package net.walseth.hospital.billingservice.config

import net.walseth.hospital.billingservice.createpatientaccount.PatientAccountDao
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.sqlobject.SqlObjectPlugin
import org.jdbi.v3.sqlobject.kotlin.KotlinSqlObjectPlugin
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class JdbiConfig {

    @Bean
    fun jdbi(dataSource: DataSource): Jdbi =
        Jdbi.create(dataSource)
            .installPlugin(KotlinPlugin())
            .installPlugin(SqlObjectPlugin())
            .installPlugin(KotlinSqlObjectPlugin())

    @Bean
    fun patientAccountRepository(jdbi: Jdbi): PatientAccountDao =
        jdbi.onDemand(PatientAccountDao::class.java)
}
