package com.ruslangrigoriev.exchangerates.di

import android.content.Context
import com.ruslangrigoriev.exchangerates.data.dataBase.CurrencyDao
import com.ruslangrigoriev.exchangerates.data.api.ApiService
import com.ruslangrigoriev.exchangerates.data.repository.RepositoryImpl
import com.ruslangrigoriev.exchangerates.domain.useCases.GetCurrencyRatesUseCase
import com.ruslangrigoriev.exchangerates.domain.useCases.PeriodicUpdateCurrencyRatesUseCase
import com.ruslangrigoriev.exchangerates.domain.Repository
import com.ruslangrigoriev.exchangerates.domain.useCases.UpdateCurrencyRatesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRepository(
        @ApplicationContext appContext: Context,
        apiService: ApiService,
        currencyDao: CurrencyDao
    ): Repository {
        return RepositoryImpl(appContext, apiService, currencyDao)
    }

    @Singleton
    @Provides
    fun provideGetCurrencyRatesUseCase(
        repository: Repository
    ): GetCurrencyRatesUseCase {
        return GetCurrencyRatesUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideUpdateCurrencyRatesUseCase(
        repository: Repository
    ): UpdateCurrencyRatesUseCase {
        return UpdateCurrencyRatesUseCase(repository)
    }

    @Singleton
    @Provides
    fun providePeriodicUpdateCurrencyRatesUseCase(
        repository: Repository
    ): PeriodicUpdateCurrencyRatesUseCase {
        return PeriodicUpdateCurrencyRatesUseCase(repository)
    }


}