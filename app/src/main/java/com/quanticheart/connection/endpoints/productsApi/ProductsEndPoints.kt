package com.quanticheart.connection.endpoints.productsApi

import com.quanticheart.connection.endpoints.entity.Data
import com.quanticheart.connection.endpoints.entity.DataList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsEndPoints {
    @GET("datalist?limit=5&offset=0")
    fun getProductsList(): Call<DataList>

    @GET("getdata/{id}")
    fun getProductByID(@Path("id") id: String): Call<Data>
}