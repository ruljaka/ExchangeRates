package com.ruslangrigoriev.exchangerates.data.network

import com.ruslangrigoriev.exchangerates.data.dto.CurrencyRatesInfo
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("daily_json.js")
    suspend fun getCurrencyRates(): Response<CurrencyRatesInfo>
}