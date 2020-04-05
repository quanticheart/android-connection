package com.quanticheart.connection.endpoints.entity

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("data1") val data1: String,
    @SerializedName("data2") val data2: String,
    @SerializedName("data2") val data3: String
)