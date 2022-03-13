package com.ruslangrigoriev.exchangerates.domain.useCases

import com.ruslangrigoriev.exchangerates.domain.Repository

class GetCurrencyRatesUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.getCurrencyRates()
}