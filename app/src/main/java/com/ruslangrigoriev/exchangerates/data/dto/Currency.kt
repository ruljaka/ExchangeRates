package com.ruslangrigoriev.exchangerates.data.dto


import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("ID")
    val iD: String,
    @SerializedName("CharCode")
    val charCode: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Nominal")
    val nominal: Int,
    @SerializedName("Value")
    val value: Double
)