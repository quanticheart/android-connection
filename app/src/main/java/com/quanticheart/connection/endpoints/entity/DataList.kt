@file:Suppress("unused")

package com.quanticheart.connection.endpoints.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataList {
    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("next")
    @Expose
    var next: String? = null
    @SerializedName("previous")
    @Expose
    var previous: String? = null
    @SerializedName("results")
    @Expose
    var results: List<Data>? = null
}