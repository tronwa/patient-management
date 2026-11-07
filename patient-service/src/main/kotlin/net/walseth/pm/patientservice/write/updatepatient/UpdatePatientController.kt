package net.walseth.pm.patientservice.write.updatepatient

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import net.walseth.pm.patientservice.common.ids.PatientId
import net.walseth.pm.patientservice.read.repositories.PatientViewRepository
import org.axonframework.messaging.commandhandling.gateway.CommandGateway
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("hospital/v1/update-patient")
@Tag(name = "Patient Updates", description = "Endpoints for updating patient information")
class UpdatePatientController(
    val patientViewRepository: PatientViewRepository,
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    val commandGateway: CommandGateway
) {

    @PutMapping("/{patientId}")
    @Operation(
        summary = "Update patient information",
        description = "Update one or more fields of an existing patient record"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Patient updated successfully",
                content = [Content()]
            ),
            ApiResponse(responseCode = "400", description = "Invalid request data"),
            ApiResponse(responseCode = "404", description = "Patient not found")
        ]
    )
    fun updatePatient(
        @PathVariable
        @Parameter(description = "The unique identifier of the patient to update")
        patientId: String,
        @RequestBody request: UpdatePatientRequest
    ): ResponseEntity<Unit> {
        require(patientViewRepository.existsById(patientId)) { "Patient with id: $patientId not found" }
        val patientId = PatientId(patientId)
        commandGateway.send(UpdatePatientCommand.from(request, patientId))
        return ResponseEntity.ok().build()
    }
}