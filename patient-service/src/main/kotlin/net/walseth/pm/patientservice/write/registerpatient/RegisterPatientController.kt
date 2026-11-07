package net.walseth.pm.patientservice.write.registerpatient

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
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
@Tag(name = "Patient Registration", description = "Endpoints for registering new patients")
class RegisterPatientController(
    val patientViewRepository: PatientViewRepository,
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    val commandGateway: CommandGateway,
) {

    @PostMapping
    @Operation(
        summary = "Register a new patient",
        description = "Create a new patient record in the system"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Patient registered successfully",
                content = [Content()]
            ),
            ApiResponse(responseCode = "400", description = "Invalid request or email already exists"),
            ApiResponse(responseCode = "409", description = "Patient with this email already exists")
        ]
    )
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