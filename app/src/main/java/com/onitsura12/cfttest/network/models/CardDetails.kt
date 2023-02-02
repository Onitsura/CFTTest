package com.onitsura12.cfttest.network.models

import com.google.gson.annotations.SerializedName
import java.net.URL

data class CardDetails(
    @SerializedName("scheme")
    val scheme: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("prepaid")
    val prepaid: Boolean?,
    @SerializedName("country")
    val country: Country?,
    @SerializedName("bank")
    val bank: Bank?
)

data class Bank(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: URL?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("city")
    val city: String?
)

data class Country(
    @SerializedName("numeric")
    val numeric: Int?,
    @SerializedName("alpha2")
    val aplha2: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("emoji")
    val emoji: String?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("latitude")
    val latitude: Long?,
    @SerializedName("longitude")
    val longitude: Long?
)
