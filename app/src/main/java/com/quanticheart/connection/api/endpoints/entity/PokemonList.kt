@file:Suppress("unused")

package com.quanticheart.connection.api.endpoints.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PokemonList {
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
    var results: List<PokemonResultList>? = null

    inner class PokemonResultList {
        @SerializedName("name")
        @Expose
        var name: String? = null
        @SerializedName("url")
        @Expose
        var url: String? = null
    }
}