package net.walseth.hospital.billingservice.ids

import java.util.UUID

data class AccountId(val accountId: String) {

    override fun toString(): String = accountId

    companion object {
        fun randomId() = AccountId(UUID.randomUUID().toString())
    }
}
