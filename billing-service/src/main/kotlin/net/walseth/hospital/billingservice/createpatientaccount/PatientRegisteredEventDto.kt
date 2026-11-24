package net.walseth.hospital.billingservice.createpatientaccount

import java.time.LocalDate

data class PatientRegisteredEventDto(
    val patientId: String,
    val name: String,
    val email: String,
    val address: String,
    val dateOfBirth: LocalDate,
    val registrationDate: LocalDate,
)
