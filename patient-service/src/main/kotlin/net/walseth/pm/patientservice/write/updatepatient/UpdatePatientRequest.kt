package net.walseth.pm.patientservice.write.updatepatient

data class UpdatePatientRequest(
    val name: String?,
    val email: String?,
    val address: String?,
) {
    init {
        require(
            name?.isNotEmpty() == true ||
                    email?.isNotEmpty() == true ||
                    address?.isNotEmpty() == true) { "Alle parametre kan ikke være tomme" }
    }
}