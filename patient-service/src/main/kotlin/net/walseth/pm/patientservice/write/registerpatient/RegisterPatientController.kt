package net.walseth.pm.patientservice.write.registerpatient

import net.walseth.pm.patientservice.common.ids.PatientId
import net.walseth.pm.patientservice.read.repositories.PatientViewRepository
import org.axonframework.messaging.commandhandling.gateway.CommandGateway
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.time.LocalDate
import java.util.UUID

@RestController
@RequestMapping("/hospital/v1/register-patient/")
class RegisterPatientController(
    val patientViewRepository: PatientViewRepository,
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    val commandGateway: CommandGateway,
) {

    @PostMapping
    fun registerPatient(
        @RequestBody patientRequest: RegisterPatientRequest
    ): ResponseEntity<Unit> {
        require(!patientViewRepository.existsByEmail(patientRequest.email)) { "email already exists" }
        val patientId = PatientId("Patient:${UUID.randomUUID().toString()}")
        val command = RegisterPatientCommand(
            patientId,
            patientRequest.name,
            patientRequest.email,
            patientRequest.address,
            patientRequest.dateOfBirth,
        )
        commandGateway.send(command)
        return ResponseEntity.created(URI.create("/hospital/v1/patients/${patientId}")).build()
    }
}