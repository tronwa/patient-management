package net.walseth.pm.patientservice.write.registerpatient

import java.time.LocalDate

data class RegisterPatientRequest(
    val name: String,
    val email: String,
    val address: String,
    val dateOfBirth: LocalDate,
)