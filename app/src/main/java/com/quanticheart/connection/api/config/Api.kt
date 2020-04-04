package com.quanticheart.connection.api.config

import android.util.Log
import com.quanticheart.conn.client.RetrofitCreate
import com.quanticheart.conn.extentions.conn
import com.quanticheart.connection.api.config.headers.ApiConfig
import com.quanticheart.connection.api.endpoints.EndPoints

object Api {
    fun test() {
        val config = ApiConfig()
        config.header["Add-after"] = "Added Success"
        config.header.put("Add-after-2", "Added Success")
        config.addHeader("Add-after-3", "Added Success")

        val api = RetrofitCreate.createConn<EndPoints>(config)
        api.getPokemonList().conn({
            Log.w("Lista", it.results.toString())
        }, {

        })
    }
}