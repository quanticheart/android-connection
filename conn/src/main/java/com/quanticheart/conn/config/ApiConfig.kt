package com.quanticheart.conn.config

abstract class ApiConfig {
    abstract var baseUrl: String
    open var header: HashMap<String, String> = HashMap()
    open var connectionTimeOutMin: Int = 2
    open var consoleLog = false

    fun addHeader(key: String, data: String) {
        header[key] = data
    }

    fun createHeader(): HashMap<String, String> = HashMap()
}