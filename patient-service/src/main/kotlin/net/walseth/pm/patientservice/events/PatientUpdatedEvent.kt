package net.walseth.pm.patientservice.events

import net.walseth.pm.patientservice.common.ids.PatientId
import net.walseth.pm.patientservice.write.updatepatient.UpdatePatientCommand
import org.axonframework.eventsourcing.annotation.EventTag

data class PatientUpdatedEvent(
    @EventTag("patientId")
    val patientId: PatientId,
    val name: String?,
    val email: String?,
    val address: String?,
) {
    companion object {
        fun from(command: UpdatePatientCommand): PatientUpdatedEvent {
            return PatientUpdatedEvent(
                command.patientId,
                command.name,
                command.email,
                command.address,
            )
        }
    }
}
