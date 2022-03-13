package com.ruslangrigoriev.exchangerates.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ruslangrigoriev.exchangerates.domain.model.CurrencyRatesInfo
import com.ruslangrigoriev.exchangerates.data.api.ApiService
import com.ruslangrigoriev.exchangerates.data.api.CustomJsonConverter
import com.ruslangrigoriev.exchangerates.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverter(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(CurrencyRatesInfo::class.java, CustomJsonConverter())
            .create()
    }
}