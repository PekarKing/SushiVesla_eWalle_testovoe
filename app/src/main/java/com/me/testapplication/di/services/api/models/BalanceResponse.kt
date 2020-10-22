package com.me.testapplication.di.services.api.models

data class BalanceResponse(
    val walletId: String,
    val balance: String,
    val currency: String
)