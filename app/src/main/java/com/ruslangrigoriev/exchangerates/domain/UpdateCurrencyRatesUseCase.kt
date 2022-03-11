package com.ruslangrigoriev.exchangerates.domain

class UpdateCurrencyRatesUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.updateCurrencyRates()
}