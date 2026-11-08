package net.walseth.hospital.billingservice

import io.kotest.core.extensions.ApplyExtension
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(TestcontainersConfiguration::class)
@ApplyExtension(SpringExtension::class)
class BillingServiceApplicationTests: DescribeSpec( {
    describe("Spring context loads") {
    }

})