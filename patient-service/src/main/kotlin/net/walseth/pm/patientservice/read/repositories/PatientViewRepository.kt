package net.walseth.pm.patientservice.read.repositories

import net.walseth.pm.patientservice.read.views.PatientView
import org.springframework.data.repository.CrudRepository

interface PatientViewRepository: CrudRepository<PatientView, String> {
}