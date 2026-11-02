package net.walseth.pm.patientservice.write.entities

import net.walseth.pm.patientservice.common.ids.PatientId
import net.walseth.pm.patientservice.events.PatientRegisteredEvent
import net.walseth.pm.patientservice.events.PatientUpdatedEvent
import org.axonframework.eventsourcing.annotation.EventCriteriaBuilder
import org.axonframework.eventsourcing.annotation.EventSourcingHandler
import org.axonframework.eventsourcing.annotation.Snapshotting
import org.axonframework.eventsourcing.annotation.reflection.EntityCreator
import org.axonframework.extension.spring.stereotype.EventSourced
import org.axonframework.messaging.eventstreaming.EventCriteria
import org.axonframework.messaging.eventstreaming.Tag
import java.time.LocalDate

@EventSourced(idType = PatientId::class)
@Snapshotting(afterEvents = 10)
class Patient @EntityCreator constructor() {
    private var id: PatientId? = null
    private var name: String? = null
    private var address: String? = null
    private var email: String? = null
    private var dateOfBirth: LocalDate? = null
    private var registrationDate: LocalDate? = null

    @EventSourcingHandler
    fun handleRegistration(event: PatientRegisteredEvent) {
        this.id = event.patientId
        this.name = event.name
        this.email = event.email
        this.address = event.address
        this.dateOfBirth = event.dateOfBirth
        this.registrationDate = event.registrationDate
    }

    @EventSourcingHandler
    fun handleUpdate(event: PatientUpdatedEvent) {
        event.name?.let { name = it }
        event.email?.let { email = it }
        event.address?.let { address = it }
    }

    companion object {
        @EventCriteriaBuilder
        fun criteria(id: PatientId) {
            EventCriteria.havingTags(Tag("patientId", id.id))
        }
    }
}