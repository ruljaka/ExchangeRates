package com.ruslangrigoriev.exchangerates.domain.useCases

import com.ruslangrigoriev.exchangerates.domain.Repository

class UpdateCurrencyRatesUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.updateCurrencyRates()
}