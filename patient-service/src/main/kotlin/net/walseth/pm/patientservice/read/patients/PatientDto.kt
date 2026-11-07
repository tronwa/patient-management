package net.walseth.pm.patientservice.read.patients

import io.swagger.v3.oas.annotations.media.Schema
import net.walseth.pm.patientservice.common.ids.PatientId
import net.walseth.pm.patientservice.read.views.PatientView
import java.time.LocalDate

@Schema(description = "Patient data transfer object containing patient information")
data class PatientDto(
    @Schema(description = "Unique identifier of the patient")
    val id: String,
    @Schema(description = "Full name of the patient")
    val name: String,
    @Schema(description = "Email address of the patient", example = "john.doe@example.com")
    val email: String,
    @Schema(description = "Physical address of the patient")
    val address: String,
    @Schema(description = "Date of birth of the patient")
    val dateOfBirth: LocalDate,
    @Schema(description = "Date when the patient was registered in the system")
    val registrationDate: LocalDate,
){
    companion object {
        fun from(view: PatientView) = PatientDto(
            view.id,
            view.name,
            view.email,
            view.address,
            view.dateOfBirth,
            view.registeredDate
        )
    }
}
