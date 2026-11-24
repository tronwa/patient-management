package net.walseth.hospital.billingservice.createpatientaccount

import net.walseth.hospital.billingservice.ids.AccountId
import org.jdbi.v3.spring.JdbiRepository
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

@JdbiRepository
interface PatientAccountDao {

    @SqlUpdate("""
        INSERT INTO patient_accounts (account_id, patient_id, name, email, address)
        VALUES (:accountId, :patientId, :name, :email, :address)
    """)
    fun save(@BindBean account: PatientAccount)

    @SqlQuery("""
        SELECT patient_id, patient_name, name, email, address
        FROM patient_account p
        where account.id=:accountId
    """)
    fun findByAccountId(@Bind accountId: AccountId): PatientAccount
}
