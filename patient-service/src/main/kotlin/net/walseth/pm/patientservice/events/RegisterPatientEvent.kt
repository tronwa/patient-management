package net.walseth.pm.patientservice.events

import net.walseth.pm.patientservice.common.ids.PatientId
import net.walseth.pm.patientservice.write.registerpatient.RegisterPatientCommand
import org.axonframework.eventsourcing.annotation.EventTag
import java.time.LocalDate

data class RegisterPatientEvent(
    @EventTag("patientId")
    val patientId: PatientId,
    val name: String,
    val email: String,
    val address: String,
    val dateOfBirth: LocalDate,
    val registrationDate: LocalDate = LocalDate.now(),
) {
    companion object {
        fun from(command: RegisterPatientCommand): RegisterPatientEvent {
            return RegisterPatientEvent(
                command.id,
                command.name,
                command.email,
                command.address,
                command.dateOfBirth,
            )
        }
    }
}