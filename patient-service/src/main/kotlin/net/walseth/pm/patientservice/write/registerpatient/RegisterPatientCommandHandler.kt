package net.walseth.pm.patientservice.write.registerpatient

import net.walseth.pm.patientservice.events.PatientRegisteredEvent
import org.axonframework.messaging.commandhandling.annotation.CommandHandler
import org.axonframework.messaging.eventhandling.gateway.EventAppender
import org.springframework.stereotype.Component

@Component
class RegisterPatientCommandHandler {

    @CommandHandler
    fun registerPatient(
        command: RegisterPatientCommand,
        appender: EventAppender,
    ) {
      val event = PatientRegisteredEvent.from(command)
      appender.append(event)
    }
}