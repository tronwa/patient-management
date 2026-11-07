package net.walseth.pm.patientservice.write.registerpatient

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "Request to register a new patient")
data class RegisterPatientRequest(
    @Schema(description = "Full name of the patient", example = "John Doe")
    val name: String,
    @Schema(description = "Email address of the patient", example = "john.doe@example.com")
    val email: String,
    @Schema(description = "Physical address of the patient", example = "123 Main St, Springfield")
    val address: String,
    @Schema(description = "Date of birth of the patient")
    val dateOfBirth: LocalDate,
)