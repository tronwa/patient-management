package net.walseth.pm.patientservice.read.patients

import net.walseth.pm.patientservice.read.repositories.PatientViewRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/hospital/v1/patients/"])
class ReadPatientController(private val repository: PatientViewRepository) {

    @GetMapping("{patientId}")
    fun getPatient(@PathVariable patientId: String): ResponseEntity<PatientDto> =
        ResponseEntity.of(
            repository.findById(patientId)
                .map { PatientDto.from(it) }
        )

    @GetMapping
    fun findAllPatients(): ResponseEntity<List<PatientDto>> {
        val patientList =  repository.findAll()
            .map { PatientDto.from(it) }
        return ResponseEntity.ok(patientList)
    }
}