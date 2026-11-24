package net.walseth.hospital.billingservice.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tools.jackson.databind.json.JsonMapper

@Configuration
class RabbitMqConfig {

    @Bean
    fun patientRegistrationQueue(): Queue = Queue(PATIENT_REGISTRATION_QUEUE)

    @Bean
    fun patientExchange(): DirectExchange = DirectExchange(PATIENT_EXCHANGE)

    @Bean
    fun patientRegistrationBinding(
        patientRegistrationQueue: Queue,
        patientExchange: DirectExchange,
    ): Binding = BindingBuilder
        .bind(patientRegistrationQueue)
        .to(patientExchange)
        .with(NEW_PATIENT_KEY)

    @Bean
    fun messageConverter(jsonMapper: JsonMapper): MessageConverter =
        JacksonJsonMessageConverter(jsonMapper)

    companion object {
        const val PATIENT_REGISTRATION_QUEUE = "patient_registration"
        const val PATIENT_EXCHANGE = "patient_exchange"
        const val NEW_PATIENT_KEY = "new_patient"
    }
}
