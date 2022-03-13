package com.ruslangrigoriev.exchangerates.domain.useCases

import com.ruslangrigoriev.exchangerates.domain.Repository

class PeriodicUpdateCurrencyRatesUseCase(
    private val repository: Repository
) {
    operator fun invoke() = repository.periodicUpdate()
}