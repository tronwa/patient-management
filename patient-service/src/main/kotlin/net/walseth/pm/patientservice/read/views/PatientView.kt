package net.walseth.pm.patientservice.read.views

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("patient-view")
data class PatientView(
    @Id
    @Column("id")
    val _id: String,
    val name: String,
    val email: String,
    val address: String,
    val dateOfBirth: LocalDate,
    val registeredDate: LocalDate,
    @Transient
    val new: Boolean = true
): Persistable<String> {
    override fun getId() = _id
    override fun isNew() = new
}