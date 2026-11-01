package net.walseth.pm.patientservice.common.ids

@JvmInline
value class PatientId(val id: String) {
    init {
        require(id.isNotBlank()) { "Id cannot be blank" }
        require(id.length <= 31) { "Id must be no more than 31" }
        require(id.startsWith("Patient:"))
    }
}