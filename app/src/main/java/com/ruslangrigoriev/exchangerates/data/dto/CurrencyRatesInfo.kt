package com.ruslangrigoriev.exchangerates.data.dto


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CurrencyRatesInfo(
    @PrimaryKey()
    val id: Int,
    val date: String,
    val updated: String,
    val currency: MutableList<Currency>
)