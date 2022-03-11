package com.ruslangrigoriev.exchangerates.domain

class PeriodicUpdateCurrencyRatesUseCase(
    private val repository: Repository
) {
    operator fun invoke() = repository.periodicUpdate()
}