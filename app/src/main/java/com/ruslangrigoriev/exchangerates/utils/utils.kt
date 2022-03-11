package com.ruslangrigoriev.exchangerates.utils

import android.text.Editable
import com.ruslangrigoriev.exchangerates.presentation.MainViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun Double.format(digits: Int) = "%.${digits}f".format(Locale.US, this)


fun String.formatDate(): String {
    val firstFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.ENGLISH)
    if (this.isNotEmpty()) {
        val localDate = LocalDateTime.parse(this, firstFormatter)
        val secondFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm", Locale.ENGLISH)
        return localDate.format(secondFormatter)
    }
    return "not found"
}

fun extGetCurrentDate(): String {
    val sdf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
    return sdf.format(LocalDateTime.now())
}

fun extCalculateFrom(text: Editable, viewModel: MainViewModel): String {
    with(viewModel) {
        val value = text.toString().toDouble()
        val rateFrom = currencyList[indexConvertFrom].value
        val rateTo = currencyList[indexConvertTo].value
        val nominalFrom = currencyList[indexConvertFrom].nominal.toDouble()
        val nominalTo = currencyList[indexConvertTo].nominal.toDouble()
        val result = (rateFrom.div(nominalFrom) * value).div(rateTo.div(nominalTo))
        return result.format(4)
    }
}

fun extCalculateTo(text: Editable, viewModel: MainViewModel): String {
    with(viewModel) {
        val value = text.toString().toDouble()
        val rateFrom = currencyList[indexConvertFrom].value
        val rateTo = currencyList[indexConvertTo].value
        val nominalFrom = currencyList[indexConvertFrom].nominal.toDouble()
        val nominalTo = currencyList[indexConvertTo].nominal.toDouble()
        val result = (rateTo.div(nominalTo) * value).div(rateFrom.div(nominalFrom))
        return result.format(4)
    }
}

fun extGetLabelFrom(type: String, viewModel: MainViewModel): String {
    with(viewModel) {
        val fromRate = currencyList[indexConvertFrom].value
        val fromCharCode = currencyList[indexConvertFrom].charCode
        val fromNominal = currencyList[indexConvertFrom].nominal
        val toRate = currencyList[indexConvertTo].value
        val toCharCode = currencyList[indexConvertTo].charCode
        val toNominal = currencyList[indexConvertTo].nominal
        val fromResultValue = fromRate.div(toRate) * toNominal
        val toResultValue = toRate.div(fromRate) * fromNominal
        return if (type == FROM) {
            "$fromNominal $fromCharCode = ${fromResultValue.format(4)} $toCharCode"
        } else {
            "$toNominal $toCharCode = ${toResultValue.format(4)} $fromCharCode"
        }
    }
}

