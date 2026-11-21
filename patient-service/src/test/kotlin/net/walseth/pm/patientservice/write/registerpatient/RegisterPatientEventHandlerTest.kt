package net.walseth.pm.patientservice.write.registerpatient

import io.kotest.core.extensions.ApplyExtension
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import net.walseth.pm.patientservice.TestcontainersConfiguration
import net.walseth.pm.patientservice.common.ids.PatientId
import net.walseth.pm.patientservice.events.PatientRegisteredEvent
import net.walseth.pm.patientservice.read.repositories.PatientViewRepository
import org.axonframework.extension.springboot.test.AxonSpringBootTest
import org.axonframework.test.fixture.AxonTestFixture
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import java.time.LocalDate

@AxonSpringBootTest
@ApplyExtension(SpringExtension::class)
@Import(TestcontainersConfiguration::class)
class RegisterPatientEventHandlerTest @Autowired constructor(
    val fixture: AxonTestFixture,
    val patientViewRepository: PatientViewRepository,
) : DescribeSpec({
    val evt = PatientRegisteredEvent(
        patientId = PatientId.randomId(),
        "Gorgon Zola",
        "gorgon@zola.com",
        "Litt her og der",
        LocalDate.of(2019, 12, 25),
        LocalDate.now()
    )
    describe("Lagring av pasient") {
        it("Happy path") {
            fixture.given()
                .`when`()
                .event(evt)
                .then()
                .success()
                .await { then ->
                    then.expect { _ ->
                        val savedPatient = patientViewRepository.findById(evt.patientId.id).get()
                        savedPatient shouldNotBe null
                        savedPatient.name shouldBe evt.name
                        savedPatient.email shouldBe evt.email
                        savedPatient.address shouldBe evt.address
                    }
                }
        }
    }

})