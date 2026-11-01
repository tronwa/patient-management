package net.walseth.pm.patientservice.read.patients

import net.walseth.pm.patientservice.common.ids.PatientId
import net.walseth.pm.patientservice.read.views.PatientView
import java.time.LocalDate

data class PatientDto(
    val id: String,
    val name: String,
    val email: String,
    val address: String,
    val dateOfBirth: LocalDate,
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
