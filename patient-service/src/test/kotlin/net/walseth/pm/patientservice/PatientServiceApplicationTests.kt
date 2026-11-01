package net.walseth.pm.patientservice

import io.kotest.core.extensions.ApplyExtension
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(TestcontainersConfiguration::class)
@SpringBootTest
@ApplyExtension(SpringExtension::class)
class PatientServiceApplicationTests: DescribeSpec({
	describe( "Spring context Loads") {
	}
})
