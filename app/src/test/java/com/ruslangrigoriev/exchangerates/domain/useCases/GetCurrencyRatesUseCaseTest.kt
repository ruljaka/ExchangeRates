package com.ruslangrigoriev.exchangerates.domain.useCases

import com.google.common.truth.Truth.assertThat
import com.ruslangrigoriev.exchangerates.domain.model.CurrencyRatesInfo
import com.ruslangrigoriev.exchangerates.data.repository.FakeRepository
import com.ruslangrigoriev.exchangerates.domain.Repository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCurrencyRatesUseCaseTest {

    private lateinit var getCurrencyRates: GetCurrencyRatesUseCase
    private lateinit var fakeRepository: Repository
    private  val expected = CurrencyRatesInfo(
        1,
        "date",
        "date",
        mutableListOf())

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        getCurrencyRates = GetCurrencyRatesUseCase(fakeRepository)

        runBlocking {
            fakeRepository.updateCurrencyRates()
        }
    }

    @Test
    fun `get rates info`(): Unit = runBlocking {
        val crt = getCurrencyRates()
        assertThat(expected).isEqualTo(crt)
    }

}
