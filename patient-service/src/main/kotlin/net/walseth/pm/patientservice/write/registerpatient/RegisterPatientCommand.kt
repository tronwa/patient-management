package net.walseth.pm.patientservice.write.registerpatient

import net.walseth.pm.patientservice.common.ids.PatientId
import java.time.LocalDate

data class RegisterPatientCommand(
    val id: PatientId,
    val name: String,
    val email: String,
    val address: String,
    val dateOfBirth: LocalDate,
)