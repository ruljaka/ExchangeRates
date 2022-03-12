package com.ruslangrigoriev.exchangerates.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruslangrigoriev.exchangerates.data.dto.Currency
import com.ruslangrigoriev.exchangerates.domain.GetCurrencyRatesUseCase
import com.ruslangrigoriev.exchangerates.domain.PeriodicUpdateCurrencyRatesUseCase
import com.ruslangrigoriev.exchangerates.domain.UpdateCurrencyRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrencyRatesUseCase: GetCurrencyRatesUseCase,
    private val updateCurrencyRatesUseCase: UpdateCurrencyRatesUseCase,
    private val periodicUpdateCurrencyRatesUseCase: PeriodicUpdateCurrencyRatesUseCase,
) : ViewModel() {

    private val _viewState = MutableLiveData<ResultState>()
    val viewState: LiveData<ResultState>
        get() = _viewState

    var indexConvertFrom = 34
    var indexConvertTo = 10

    lateinit var currencyList: List<Currency>
    lateinit var charCodeList: List<String>

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _viewState.postValue(ResultState.Failure(throwable.message))
    }

    init {
        fetchData()
        periodicUpdateCurrencyRatesUseCase()
    }

    private fun fetchData() {
        viewModelScope.launch(exceptionHandler) {
            _viewState.value = ResultState.Loading
            val currencyRatesInfo = getCurrencyRatesUseCase()
            _viewState.postValue(
                ResultState.Success(currencyRatesInfo)
            )
            val listCurrency = currencyRatesInfo.currency
            charCodeList = (listCurrency.map { item -> item.charCode })
            currencyList = listCurrency
        }
    }

    fun updateCurrencyRates() {
        _viewState.value = ResultState.Loading
        viewModelScope.launch(exceptionHandler) {
            val job = launch { updateCurrencyRatesUseCase() }
            job.join()
            fetchData()
        }
    }
}
