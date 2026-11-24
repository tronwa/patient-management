package net.walseth.hospital.billingservice.config

import net.walseth.hospital.billingservice.createpatientaccount.PatientAccountDao
import net.walseth.hospital.billingservice.ids.AccountId
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.argument.AbstractArgumentFactory
import org.jdbi.v3.core.argument.Argument
import org.jdbi.v3.core.config.ConfigRegistry
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.core.mapper.ColumnMapper
import org.jdbi.v3.spring.SpringConnectionFactory
import org.jdbi.v3.sqlobject.SqlObjectPlugin
import org.jdbi.v3.sqlobject.kotlin.KotlinSqlObjectPlugin
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.sql.Types
import javax.sql.DataSource

@Configuration
class JdbiConfig {

    @Bean
    fun jdbi(dataSource: DataSource): Jdbi =
        Jdbi.create(SpringConnectionFactory(dataSource))
            .installPlugin(KotlinPlugin())
            .installPlugin(SqlObjectPlugin())
            .installPlugin(KotlinSqlObjectPlugin())
            .registerArgument(AccountIdArgumentFactory())
            .registerColumnMapper(AccountId::class.java, ColumnMapper { rs, col, _ ->
                rs.getString(col)?.let { AccountId(it) }
            })

    @Bean
    fun patientAccountDao(jdbi: Jdbi): PatientAccountDao =
        jdbi.onDemand(PatientAccountDao::class.java)
}

private class AccountIdArgumentFactory : AbstractArgumentFactory<AccountId>(Types.VARCHAR) {
    override fun build(value: AccountId, config: ConfigRegistry): Argument =
        Argument { position, statement, _ -> statement.setString(position, value.accountId) }
}
