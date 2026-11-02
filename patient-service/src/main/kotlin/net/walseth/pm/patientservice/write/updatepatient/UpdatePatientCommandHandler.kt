package net.walseth.pm.patientservice.write.updatepatient

import net.walseth.pm.patientservice.events.PatientUpdatedEvent
import org.axonframework.messaging.commandhandling.annotation.CommandHandler
import org.axonframework.messaging.eventhandling.gateway.EventAppender
import org.springframework.stereotype.Component

@Component
class UpdatePatientCommandHandler {
    @CommandHandler
    fun handleUpdatePatientCommand(
        command: UpdatePatientCommand,
        appender: EventAppender
    ) {
        appender.append(PatientUpdatedEvent.from(command))
    }
}