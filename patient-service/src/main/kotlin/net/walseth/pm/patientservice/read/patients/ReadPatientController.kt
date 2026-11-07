package net.walseth.pm.patientservice.read.patients

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import net.walseth.pm.patientservice.read.repositories.PatientViewRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/hospital/v1/patients/"])
@Tag(name = "Patients", description = "Endpoints for retrieving patient information")
class ReadPatientController(private val repository: PatientViewRepository) {

    @GetMapping("{patientId}")
    @Operation(
        summary = "Get patient by ID",
        description = "Retrieve detailed information about a specific patient by their ID"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Patient found",
                content = [Content(schema = Schema(implementation = PatientDto::class))]
            ),
            ApiResponse(responseCode = "404", description = "Patient not found")
        ]
    )
    fun getPatient(
        @PathVariable
        @Parameter(description = "The unique identifier of the patient")
        patientId: String
    ): ResponseEntity<PatientDto> =
        ResponseEntity.of(
            repository.findById(patientId)
                .map { PatientDto.from(it) }
        )

    @GetMapping
    @Operation(
        summary = "Get all patients",
        description = "Retrieve a list of all registered patients in the system"
    )
    @ApiResponse(
        responseCode = "200",
        description = "List of patients retrieved successfully",
        content = [Content(schema = Schema(implementation = Array<PatientDto>::class))]
    )
    fun findAllPatients(): ResponseEntity<List<PatientDto>> {
        val patientList =  repository.findAll()
            .map { PatientDto.from(it) }
        return ResponseEntity.ok(patientList)
    }
}