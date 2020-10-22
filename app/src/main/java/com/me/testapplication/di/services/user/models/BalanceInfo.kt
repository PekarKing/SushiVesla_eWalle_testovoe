package com.me.testapplication.di.services.user.models

import com.me.testapplication.di.services.api.models.BalanceResponse

data class BalanceInfo(
    val walletId: String,
    val balance: String,
    val currency: String
) {

    constructor(balanceResponse: BalanceResponse) : this(
        balanceResponse.walletId,
        balanceResponse.balance,
        balanceResponse.currency
    )
}