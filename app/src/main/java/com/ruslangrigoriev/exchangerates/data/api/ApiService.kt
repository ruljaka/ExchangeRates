package com.ruslangrigoriev.exchangerates.data.api

import com.ruslangrigoriev.exchangerates.domain.model.CurrencyRatesInfo
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("daily_json.js")
    suspend fun getCurrencyRates(): Response<CurrencyRatesInfo>
}