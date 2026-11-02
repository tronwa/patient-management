package net.walseth.pm.patientservice.write.updatepatient

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
class UpdatePatientController(
    val patientViewRepository: PatientViewRepository,
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    val commandGateway: CommandGateway
) {

    @PutMapping("/{patientId}")
    fun updatePatient(
        @PathVariable patientId: String,
        @RequestBody request: UpdatePatientRequest
    ): ResponseEntity<Unit> {
        require(patientViewRepository.existsById(patientId)) { "Patient with id: $patientId not found" }
        val patientId = PatientId(patientId)
        commandGateway.send(UpdatePatientCommand.from(request, patientId))
        return ResponseEntity.ok().build()
    }
}