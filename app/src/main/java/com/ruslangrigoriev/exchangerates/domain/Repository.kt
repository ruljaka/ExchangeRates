package com.ruslangrigoriev.exchangerates.domain

import com.ruslangrigoriev.exchangerates.domain.model.CurrencyRatesInfo

interface Repository {
    suspend fun getCurrencyRates(): CurrencyRatesInfo
    suspend fun updateCurrencyRates()
    fun periodicUpdate()
}