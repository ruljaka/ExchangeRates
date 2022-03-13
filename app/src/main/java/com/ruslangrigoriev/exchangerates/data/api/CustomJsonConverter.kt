package com.ruslangrigoriev.exchangerates.data.api

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.ruslangrigoriev.exchangerates.domain.model.Currency
import com.ruslangrigoriev.exchangerates.domain.model.CurrencyRatesInfo
import com.ruslangrigoriev.exchangerates.utils.extGetCurrentDate
import java.lang.reflect.Type

class CustomJsonConverter : JsonDeserializer<CurrencyRatesInfo> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CurrencyRatesInfo? {
        return json?.asJsonObject?.let {
            val date = it.get("Date").asString
            val valute = it.get("Valute").asJsonObject
            val currencyList = mutableListOf<Currency>()
            val currentDate = extGetCurrentDate()
            //добавление всех полей "Valute" в список
            currencyList.add(jsonToCurrency(valute.get("AUD").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("AZN").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("GBP").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("AMD").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("BYN").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("BGN").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("BRL").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("HUF").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("HKD").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("DKK").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("USD").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("EUR").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("INR").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("KZT").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("CAD").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("KGS").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("CNY").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("MDL").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("NOK").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("PLN").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("RON").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("XDR").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("SGD").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("TJS").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("TRY").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("TMT").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("UZS").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("UAH").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("CZK").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("SEK").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("CHF").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("ZAR").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("KRW").asJsonObject))
            currencyList.add(jsonToCurrency(valute.get("JPY").asJsonObject))
            CurrencyRatesInfo(
                id = 0,
                date = date,
                updated = currentDate,
                currency = currencyList
            )
        }
    }

    private fun jsonToCurrency(jsonObject: JsonObject): Currency {
        val type = object : TypeToken<Currency>() {}.type
        val gson = Gson()
        return gson.fromJson(jsonObject, type)
    }

}