package net.walseth.pm.patientservice.write.updatepatient

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Request to update patient information")
data class UpdatePatientRequest(
    @Schema(description = "Updated full name of the patient", example = "John Doe")
    val name: String?,
    @Schema(description = "Updated email address of the patient", example = "john.doe@example.com")
    val email: String?,
    @Schema(description = "Updated physical address of the patient", example = "123 Main St, Springfield")
    val address: String?,
) {
    init {
        require(
            name?.isNotEmpty() == true ||
                    email?.isNotEmpty() == true ||
                    address?.isNotEmpty() == true) { "Alle parametre kan ikke være tomme" }
    }
}