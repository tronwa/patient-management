package net.walseth.pm.patientservice.write.updatepatient

import net.walseth.pm.patientservice.common.ids.PatientId
import org.axonframework.modelling.annotation.TargetEntityId

data class UpdatePatientCommand(
    @TargetEntityId
    val patientId: PatientId,
    val name: String?,
    val email: String?,
    val address: String?,
) {
    companion object {
        fun from(request: UpdatePatientRequest, id: PatientId): UpdatePatientCommand {
            return UpdatePatientCommand(
                id,
                request.name,
                request.email,
                request.address,
            )
        }
    }
}
