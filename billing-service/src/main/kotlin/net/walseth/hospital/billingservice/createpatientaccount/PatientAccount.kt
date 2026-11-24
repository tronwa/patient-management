package net.walseth.hospital.billingservice.createpatientaccount

import net.walseth.hospital.billingservice.ids.AccountId

data class PatientAccount(
    val accountId: AccountId,
    val patientId: String,
    val name: String,
    val email: String,
    val address: String,
)
