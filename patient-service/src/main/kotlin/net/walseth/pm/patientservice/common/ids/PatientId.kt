package net.walseth.pm.patientservice.common.ids

import java.util.UUID

@JvmInline
value class PatientId(val id: String) {
    init {
        require(id.isNotBlank()) { "Id cannot be blank" }
        require(id.length <= 64) { "Id must be no more than 31" }
        require(id.startsWith("Patient:"))
    }

    override fun toString(): String = id

    companion object {
        fun randomId(): PatientId = PatientId("Patient:${UUID.randomUUID().toString()}")
    }
}