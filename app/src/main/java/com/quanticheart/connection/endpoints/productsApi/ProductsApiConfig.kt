package com.quanticheart.connection.endpoints.productsApi

import com.quanticheart.conn.config.ApiConfig
import com.quanticheart.connection.BuildConfig

class ProductsApiConfig() : ApiConfig() {
    override var baseUrl: String = "https://data2.co/api/v3/"
    override var header: HashMap<String, String> = createHeader().apply {
        put("Api-Key", "TESTES@2")
        put("Api-Key-2", "TESTES-3")
        put("Api-APP-KEY", BuildConfig.APPLICATION_ID)
    }
    override var connectionTimeOutMin: Int = 2

}