package net.walseth.pm.patientservice.write.updatepatient

import io.kotest.core.extensions.ApplyExtension
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import net.walseth.pm.patientservice.TestcontainersConfiguration
import net.walseth.pm.patientservice.common.ids.PatientId
import net.walseth.pm.patientservice.events.PatientRegisteredEvent
import net.walseth.pm.patientservice.events.PatientUpdatedEvent
import org.axonframework.extension.springboot.test.AxonSpringBootTest
import org.axonframework.test.fixture.AxonTestFixture
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event
import java.time.LocalDate
import java.time.LocalDateTime

@AxonSpringBootTest
@ApplyExtension(SpringExtension::class)
@Import(TestcontainersConfiguration::class)
class UpdatePatientCommandHandlerTest @Autowired constructor(
    val fixture: AxonTestFixture
): DescribeSpec({
    val patientId = PatientId("Patient:bingobongo")
    val createdEvent = PatientRegisteredEvent(
        patientId,
        "Gorgon Zola",
        "gorgon@zola.com",
        "214 Main Street",
        LocalDate.of(2020, 1, 1,),
        LocalDate.now().minusDays(5),
    )
    describe("endring av email skal persisteres") {
        fixture.given()
            .event(createdEvent)
            .command(
                UpdatePatientCommand(
                    patientId = patientId,
                    name = "Gorgon Emile Zola",
                    email = null,
                    address = null
                )
            )
            .then()
            .eventsSatisfy { events ->
                events.size shouldBe 2
                val event = events[1].payload() as PatientUpdatedEvent
                event.patientId shouldBe patientId
                event.name shouldBe "Gorgon Emile Zola"
            }
    }

})