package net.walseth.pm.patientservice.write.updatepatient

import net.walseth.pm.patientservice.events.PatientUpdatedEvent
import net.walseth.pm.patientservice.read.repositories.PatientViewRepository
import org.axonframework.messaging.eventhandling.annotation.EventHandler
import org.springframework.stereotype.Component

@Component
class PatientUpdatedEventHandler(val patientViewRepository: PatientViewRepository) {

    @EventHandler
    fun handleUpdatedPatient(event: PatientUpdatedEvent) {
        val patientView = patientViewRepository.findById(event.patientId.id)
            .orElseThrow { IllegalArgumentException("Ukjent pasient-id ${event.patientId.id}") }
        patientView.copy(
            name = event.name ?:  patientView.name,
            email = event.email ?:  patientView.email,
            address = event.address ?:  patientView.address,
        ) .also {
            patientViewRepository.save(it)
        }

    }
}