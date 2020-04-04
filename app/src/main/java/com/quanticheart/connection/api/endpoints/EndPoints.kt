package com.quanticheart.connection.api.endpoints

import com.quanticheart.connection.api.entity.Pokemon
import com.quanticheart.connection.api.entity.PokemonList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EndPoints {
    @GET("pokemon?limit=5&offset=0")
    fun getPokemonList(): Call<PokemonList>

    @GET("pokemon/{id}")
    fun getPokemonList(@Path("id") id: String): Call<Pokemon>
}