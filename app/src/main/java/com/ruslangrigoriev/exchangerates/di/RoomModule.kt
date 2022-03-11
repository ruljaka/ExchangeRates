package com.ruslangrigoriev.exchangerates.di

import android.content.Context
import androidx.room.Room
import com.ruslangrigoriev.exchangerates.data.dataBase.AppDataBase
import com.ruslangrigoriev.exchangerates.data.dataBase.CurrencyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            "app_db"
        )
            .fallbackToDestructiveMigration()
            .build();
    }

    @Singleton
    @Provides
    fun provideFavoriteDAO(appDatabase: AppDataBase): CurrencyDao {
        return appDatabase.getCurrencyDAO()
    }
}