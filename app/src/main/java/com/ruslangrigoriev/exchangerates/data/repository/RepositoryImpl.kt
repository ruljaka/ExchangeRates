package com.ruslangrigoriev.exchangerates.data.repository

import android.content.Context
import android.util.Log
import androidx.work.*
import com.ruslangrigoriev.exchangerates.data.dataBase.CurrencyDao
import com.ruslangrigoriev.exchangerates.data.dto.Currency
import com.ruslangrigoriev.exchangerates.data.dto.CurrencyRatesInfo
import com.ruslangrigoriev.exchangerates.data.network.ApiService
import com.ruslangrigoriev.exchangerates.data.workers.UpdateWorker
import com.ruslangrigoriev.exchangerates.domain.Repository
import com.ruslangrigoriev.exchangerates.utils.WORKER_NAME
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepositoryImpl
@Inject constructor(
    private val appContext: Context,
    private val apiService: ApiService,
    private val currencyDao: CurrencyDao
) : Repository {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getCurrencyRates(): CurrencyRatesInfo {
        return withContext(ioDispatcher) {
            val valueFromDB = currencyDao.getCurrencyRates()
            if (valueFromDB != null) {
                valueFromDB
            } else {
                updateCurrencyRates()
                getCurrencyRates()
            }
        }
    }

    override suspend fun updateCurrencyRates() {
        withContext(ioDispatcher) {
            val apiResponse = apiService.getCurrencyRates()
            if (apiResponse.isSuccessful) {
                val valueFromApi = apiResponse.body()
                valueFromApi?.let {
                    it.currency.add(Currency("1", "RUB", "Российский рубль", 1, 1.0))
                    currencyDao.insertCurrencyRates(it)
                }
            } else {
                throw Throwable(apiResponse.message())
            }
        }
    }

    override fun periodicUpdate() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest = PeriodicWorkRequestBuilder<UpdateWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()
        val workManager = WorkManager.getInstance(appContext)
        workManager.enqueueUniquePeriodicWork(
            WORKER_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
        Log.d("TAG", "periodicUpdate")
    }
}