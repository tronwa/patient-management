package net.walseth.hospital.billingservice.createpatientaccount

import net.walseth.hospital.billingservice.config.RabbitMqConfig
import net.walseth.hospital.billingservice.ids.AccountId
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PatientRegistrationSubscriber(
    private val patientAccountDao: PatientAccountDao,
) {

    @Transactional
    @RabbitListener(queues = [RabbitMqConfig.PATIENT_REGISTRATION_QUEUE])
    fun receive(dto: PatientRegisteredEventDto) {
        val account = PatientAccount(
            accountId = AccountId.randomId(),
            patientId = dto.patientId,
            name = dto.name,
            email = dto.email,
            address = dto.address,
        )
        patientAccountDao.save(account)
    }
}