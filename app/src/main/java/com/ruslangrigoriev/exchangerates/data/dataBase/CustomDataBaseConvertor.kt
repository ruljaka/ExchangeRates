package com.ruslangrigoriev.exchangerates.data.dataBase

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ruslangrigoriev.exchangerates.data.dto.Currency

@ProvidedTypeConverter
object CustomDataBaseConvertor {
    private val gson = Gson()

    @TypeConverter
    fun toString(dataItems: List<Currency>): String {
        return gson.toJson(dataItems)
    }

    @TypeConverter
    fun toList(data: String): List<Currency> {
        val listType = object : TypeToken<List<Currency>>() {}.type
        return gson.fromJson(data, listType)
    }
}