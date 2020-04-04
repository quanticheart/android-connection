package com.quanticheart.connection.api.config.headers

import android.app.Activity
import com.quanticheart.conn.config.ApiConfig
import com.quanticheart.connection.BuildConfig

class ApiConfig(private val activity: Activity? = null) : ApiConfig() {
    override var baseUrl: String = "https://pokeapi.co/api/v2/"
    override var header: HashMap<String, String> = createHeader().apply {
        put("Api-Key", "TESTES")
        put("Api-Key-2", "TESTES")
        put("Api-APP-KEY", BuildConfig.APPLICATION_ID)

        activity?.let {
            put("ACTIVITY-KEY", it.localClassName)
        } ?: run {
            put("ACTIVITY-KEY", "NULL")
        }
        //OR
        put("ACTIVITY-KEY-2", activity?.localClassName ?: "NULL")

        connectionTimeOutMin = 1
    }
}