package net.walseth.pm.patientservice.read.views

import net.walseth.pm.patientservice.events.PatientRegisteredEvent
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.annotation.Version
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("patient_view")
data class PatientView(
    @Id
    @Column("id")
    val _id: String,
    val name: String,
    val email: String,
    val address: String,
    val dateOfBirth: LocalDate,
    val registeredDate: LocalDate,
    @Version
    val version: Long? = null,
    @Transient
    val new: Boolean = true
): Persistable<String> {
    override fun getId() = _id
    override fun isNew() = new

    companion object {
        fun from(event: PatientRegisteredEvent) = PatientView(
            event.patientId.id,
            event.name,
            event.email,
            event.address,
            event.dateOfBirth,
            event.registrationDate,
        )
    }
}