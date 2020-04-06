package com.quanticheart.connection.endpoints.carsApi

import android.content.Context
import com.quanticheart.conn.config.ApiConfig
import com.quanticheart.connection.BuildConfig

class CarsApiConfig(private val context: Context) : ApiConfig() {
    override var baseUrl: String = "https://data.co/api/v2/"
    override var header: HashMap<String, String> = createHeader().apply {
        put("Api-Key", "TESTES")
        put("Api-Key-2", "TESTES")
        put("Api-APP-KEY", BuildConfig.APPLICATION_ID)
        put("User-Api-Token", "fklsdflkehfuwehfiuehfjkwhfjksdhfebfjhwg")

    }
    override var consoleLog: Boolean = BuildConfig.DEBUG
}