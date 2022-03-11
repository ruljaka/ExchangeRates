package com.ruslangrigoriev.exchangerates.domain

import com.ruslangrigoriev.exchangerates.data.dto.CurrencyRatesInfo

interface Repository {
    suspend fun getCurrencyRates(): CurrencyRatesInfo
    suspend fun updateCurrencyRates()
    fun periodicUpdate()
}