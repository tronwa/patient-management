package net.walseth.pm.patientservice.write.registerpatient

import net.walseth.pm.patientservice.common.configs.RabbitMqConfig
import net.walseth.pm.patientservice.events.PatientRegisteredEvent
import net.walseth.pm.patientservice.read.patients.PatientDto
import org.axonframework.messaging.eventhandling.annotation.EventHandler
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class RegisterPatientProducer(
    val patientExchange: DirectExchange,
    val rabbitTemplate: RabbitTemplate,
) {

/*
    @EventHandler
    fun submitPatient(patientEvent: PatientRegisteredEvent) {
         rabbitTemplate.convertAndSend(
            patientExchange.name,
            RabbitMqConfig.NEW_PATIENT_KEY,
            patientEvent.toDto()
        )
    }
*/
}