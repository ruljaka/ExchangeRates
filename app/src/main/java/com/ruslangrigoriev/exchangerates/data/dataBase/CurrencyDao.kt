package com.ruslangrigoriev.exchangerates.data.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ruslangrigoriev.exchangerates.domain.model.CurrencyRatesInfo

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyRates(entity: CurrencyRatesInfo)

    @Query("SELECT * FROM CurrencyRatesInfo")
    suspend fun getCurrencyRates(): CurrencyRatesInfo?
}