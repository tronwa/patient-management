package net.walseth.pm.patientservice.common.configs

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tools.jackson.databind.json.JsonMapper

@Configuration
class RabbitMqConfig {

    @Bean
    fun rabbitAdmin(connectionFactory: ConnectionFactory): RabbitAdmin =
        RabbitAdmin(connectionFactory)

    @Bean
    fun rabbitTemplate(
        connectionFactory: ConnectionFactory,
        @Qualifier("jacksonJsonMapper") jsonMapper: JsonMapper,
    ): RabbitTemplate =
        RabbitTemplate(connectionFactory).also {
            it.messageConverter = JacksonJsonMessageConverter(jsonMapper)
        }

    @Bean
    fun newPatientQueue(): Queue = Queue(PATIENT_QUEUE_NAME)

    @Bean
    fun patientExchange(): DirectExchange = DirectExchange(PATIENT_EXCHANGE_NAME)

    @Bean
    fun newPatientBinding(): Binding =
        BindingBuilder.bind(newPatientQueue())
            .to(patientExchange())
            .with(NEW_PATIENT_KEY)

    @Bean
    fun updatePatientQueue(): Queue = Queue(UPDATE_PATIENT_QUEUE_NAME)

    @Bean
    fun updatePatientBinding(): Binding =
        BindingBuilder.bind(updatePatientQueue())
            .to(patientExchange())
            .with(UPDATE_PATIENT_KEY)

    companion object {
        const val PATIENT_QUEUE_NAME = "patient_registration"
        const val UPDATE_PATIENT_QUEUE_NAME = "update_patient_queue"
        const val PATIENT_EXCHANGE_NAME = "patient_exchange"
        const val NEW_PATIENT_KEY = "new_patient"
        const val UPDATE_PATIENT_KEY = "update_patient"
    }

}