package com.quanticheart.connection.endpoints.carsApi

import com.quanticheart.connection.endpoints.entity.Data
import com.quanticheart.connection.endpoints.entity.DataList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CarsEndPoints {
    @GET("datalist?limit=5&offset=0")
    fun getUserDataList(): Call<DataList>

    @GET("getdata/{id}")
    fun getUserByID(@Path("id") id: String): Call<Data>
}