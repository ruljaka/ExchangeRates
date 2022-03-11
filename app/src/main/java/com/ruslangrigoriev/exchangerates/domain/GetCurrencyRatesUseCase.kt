package com.ruslangrigoriev.exchangerates.domain

class GetCurrencyRatesUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.getCurrencyRates()
}