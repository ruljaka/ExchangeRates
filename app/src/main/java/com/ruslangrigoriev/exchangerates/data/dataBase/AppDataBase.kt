package com.ruslangrigoriev.exchangerates.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ruslangrigoriev.exchangerates.domain.model.CurrencyRatesInfo

@Database(entities = [CurrencyRatesInfo::class], version = 1, exportSchema = false)
@TypeConverters(CustomDataBaseConvertor::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getCurrencyDAO(): CurrencyDao
}