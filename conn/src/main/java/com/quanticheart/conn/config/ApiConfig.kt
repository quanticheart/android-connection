package com.quanticheart.conn.config

abstract class ApiConfig {
    abstract var baseUrl: String
    open var header: HashMap<String, String> = HashMap()
    var connectionTimeOutMin: Int = 2

    fun addHeader(key: String, data: String) {
        header[key] = data
    }

    fun createHeader(): HashMap<String, String> = HashMap()
}