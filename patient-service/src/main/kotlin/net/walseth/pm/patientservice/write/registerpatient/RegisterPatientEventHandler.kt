package net.walseth.pm.patientservice.write.registerpatient

import net.walseth.pm.patientservice.events.PatientRegisteredEvent
import net.walseth.pm.patientservice.read.repositories.PatientViewRepository
import net.walseth.pm.patientservice.read.views.PatientView
import org.axonframework.messaging.eventhandling.annotation.EventHandler
import org.springframework.stereotype.Component

@Component
class RegisterPatientEventHandler(val repository: PatientViewRepository) {

    @EventHandler
    fun handleRegistration(event: PatientRegisteredEvent) =
        PatientView.from(event).also(repository::save)
}