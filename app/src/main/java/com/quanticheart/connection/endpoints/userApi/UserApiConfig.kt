package com.quanticheart.connection.endpoints.userApi

import android.app.Activity
import com.quanticheart.conn.config.ApiConfig
import com.quanticheart.connection.BuildConfig

class UserApiConfig(private val activity: Activity? = null) : ApiConfig() {
    override var baseUrl: String = "https://data.co/api/v2/"
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
    }
    override var connectionTimeOutMin: Int = 1
}