package net.walseth.hospital.billingservice.createpatientaccount

import net.walseth.hospital.billingservice.ids.AccountId
import org.springframework.stereotype.Repository

@Repository
class PatientAccountRepository (
    val patientAccountDao: PatientAccountDao
) {

    fun findByAccountId(accountId: AccountId): PatientAccount? =
        patientAccountDao.findByAccountId(accountId)
}