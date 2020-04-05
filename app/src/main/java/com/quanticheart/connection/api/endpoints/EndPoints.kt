package com.quanticheart.connection.api.endpoints

import com.quanticheart.connection.api.endpoints.entity.Pokemon
import com.quanticheart.connection.api.endpoints.entity.PokemonList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EndPoints {
    @GET("pokemoni?limit=5&offset=0")
    fun getPokemonList(): Call<PokemonList>

    @GET("pokemon/{id}")
    fun getPokemonList(@Path("id") id: String): Call<Pokemon>
}