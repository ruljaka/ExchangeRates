package com.ruslangrigoriev.exchangerates.data.network

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.ruslangrigoriev.exchangerates.data.dto.Currency
import com.ruslangrigoriev.exchangerates.data.dto.CurrencyRatesInfo
import com.ruslangrigoriev.exchangerates.utils.extGetCurrentDate
import java.lang.reflect.Type

class CustomJsonConverter : JsonDeserializer<CurrencyRatesInfo> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CurrencyRatesInfo? {

        val jsonObject = json?.asJsonObject;
        jsonObject?.let {
            val date = it.get("Date").asString
            val valute = it.get("Valute").asJsonObject
            val currencyList = mutableListOf<Currency>()
            val currentDate = extGetCurrentDate()
            //такой вот неудобный Json прилетает с сервера
            currencyList.add(valuteToCurrency(valute.get("AUD").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("AZN").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("GBP").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("AMD").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("BYN").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("BGN").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("BRL").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("HUF").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("HKD").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("DKK").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("USD").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("EUR").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("INR").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("KZT").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("CAD").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("KGS").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("CNY").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("MDL").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("NOK").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("PLN").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("RON").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("XDR").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("SGD").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("TJS").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("TRY").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("TMT").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("UZS").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("UAH").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("CZK").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("SEK").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("CHF").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("ZAR").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("KRW").asJsonObject))
            currencyList.add(valuteToCurrency(valute.get("JPY").asJsonObject))
            return CurrencyRatesInfo(
                id = 0,
                date = date,
                updated = currentDate,
                currency = currencyList
            )
        }
        return null
    }

    private fun valuteToCurrency(jsonObject: JsonObject): Currency {
        val id = jsonObject.get("ID").asString
        val charCode = jsonObject.get("CharCode").asString
        val name = jsonObject.get("Name").asString
        val nominal = jsonObject.get("Nominal").asInt
        val value = jsonObject.get("Value").asDouble
        return Currency(id, charCode, name, nominal, value)
    }

}