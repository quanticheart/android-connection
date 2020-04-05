package com.quanticheart.connection.api.endpoints.config

import android.app.Activity
import android.util.Log
import com.quanticheart.conn.client.RetrofitCreate
import com.quanticheart.conn.extentions.conn
import com.quanticheart.connection.api.endpoints.EndPoints
import com.quanticheart.connection.api.endpoints.config.headers.ApiConfig

fun Activity.getPokemonList() {
    val config = ApiConfig(this)
    config.header["Add-after"] = "Added Success"
    config.header.put("Add-after-2", "Added Success")
    config.addHeader("Add-after-3", "Added Success")

    val api = RetrofitCreate.createConn<EndPoints>(config)
    api.getPokemonList().conn({
        Log.e("Lista", it?.results.toString())
    }, {
        Log.e("ERROR", it.message)
    }, 10)
}