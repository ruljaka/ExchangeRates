package com.ruslangrigoriev.exchangerates.data.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ruslangrigoriev.exchangerates.data.dataBase.CurrencyDao
import com.ruslangrigoriev.exchangerates.data.dto.Currency
import com.ruslangrigoriev.exchangerates.data.network.ApiService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class UpdateWorker
@AssistedInject constructor(
    private val apiService: ApiService,
    private val currencyDao: CurrencyDao,
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun doWork(): Result {
        return withContext(ioDispatcher) {
            try {
                val apiResponse = apiService.getCurrencyRates()
                if (apiResponse.isSuccessful) {
                    val valueFromApi = apiResponse.body()
                    valueFromApi?.let {
                        it.currency.add(Currency("1", "RUB", "Российский рубль", 1, 1.0))
                        currencyDao.insertCurrencyRates(it)
                    }
                    return@withContext Result.success()
                } else {
                    return@withContext Result.failure()
                }
            } catch (e: Exception) {
                e.message?.let { Log.d("TAG", it) }
                return@withContext Result.failure()
            }
        }
    }
}
