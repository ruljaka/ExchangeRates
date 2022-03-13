package com.ruslangrigoriev.exchangerates.presentation

import com.ruslangrigoriev.exchangerates.domain.model.CurrencyRatesInfo

sealed class ResultState {
    object Loading : ResultState()
    class Failure(val errorMessage: String?) : ResultState()
    class Success(val currencyRatesInfo: CurrencyRatesInfo) : ResultState()
}