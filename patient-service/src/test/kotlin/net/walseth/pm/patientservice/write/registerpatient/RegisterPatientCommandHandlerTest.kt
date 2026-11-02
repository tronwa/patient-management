package net.walseth.pm.patientservice.write.registerpatient

import io.kotest.core.extensions.ApplyExtension
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import net.walseth.pm.patientservice.TestcontainersConfiguration
import net.walseth.pm.patientservice.common.ids.PatientId
import net.walseth.pm.patientservice.events.PatientRegisteredEvent
import org.axonframework.extension.springboot.test.AxonSpringBootTest
import org.axonframework.test.fixture.AxonTestFixture
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import java.time.LocalDate

@AxonSpringBootTest
@ApplyExtension(SpringExtension::class)
@Import(TestcontainersConfiguration::class)
class RegisterPatientCommandHandlerTest(
    @Autowired val fixture: AxonTestFixture,
): DescribeSpec( {
    describe("Send pasientregistrering") {
        val kommando = RegisterPatientCommand(
            PatientId("Patient:bingobongo"),
            "Gorgon Zola",
            "gorgon@zola.fr",
            "Langtvekkistan",
            LocalDate.of(2019, 12, 25),
        )
        fixture.given()
            .noPriorActivity()
            .command(kommando)
            .then()
            .eventsSatisfy { events ->
                events shouldHaveSize 1
                val event = events.first().payload() as PatientRegisteredEvent
                event.patientId.id shouldBe "Patient:bingobongo"
                event.registrationDate shouldBe LocalDate.now()
            }

    }
})