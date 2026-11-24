package net.walseth.hospital.billingservice.createpatientaccount

import io.kotest.core.extensions.ApplyExtension
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldNotBe
import net.walseth.hospital.billingservice.TestcontainersConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import java.time.LocalDate
import java.time.Month

@SpringBootTest
@ApplyExtension(SpringExtension::class)
@Import(TestcontainersConfiguration::class)
class PatientRegistrationSubscriberTest @Autowired constructor(
    val patientRegistrationSubscriber: PatientRegistrationSubscriber,
    val repository: PatientAccountRepository,
) : DescribeSpec({

    describe("Happy path") {
        val registration = PatientRegisteredEventDto(
            "123-abc-123",
            "Gorgon Zola",
            "gorgon@zola.com",
            "Storevarveien 75A",
            LocalDate.of(1979, Month.DECEMBER, 25),
            LocalDate.now()
        )
        it("PatientAccount skal lagres til database") {
            patientRegistrationSubscriber.receive(registration)
            val patient = repository.findByName(registration.name)
            patient shouldNotBe null
        }
    }

})
