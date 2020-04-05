package com.quanticheart.connection.endpoints.productsApi

import android.app.Activity
import android.util.Log
import com.quanticheart.conn.client.RetrofitCreate
import com.quanticheart.conn.extentions.connect

fun Activity.getProductList() {
    val config = ProductsApiConfig()
    config.header["Add-after"] = "Added Success"

    val api = RetrofitCreate.createConn<ProductsEndPoints>(config)
    api.getProductsList().connect({
        Log.e("List Data", it.results.toString())
    }, {
        Log.e("ERROR", it.message)
    }, 5)
}

fun Activity.getProductList2() {
    val config = ProductsApiConfig()
    config.header["Add-after"] = "Added Success"

    val api = RetrofitCreate.createConn<ProductsEndPoints>(config)
    api.getProductsList().connect({
        Log.e("List Data", it.results.toString())
    }, {
        Log.e("ERROR", it.message)
    })
}

fun Activity.getProductByID(id: String) {
    val api = RetrofitCreate.createConn<ProductsEndPoints>(ProductsApiConfig())
    api.getProductByID(id).connect {
        Log.e("data", it.data1)
    }
}

fun Activity.getProductByID2(id: String) {
    val api = RetrofitCreate.createConn<ProductsEndPoints>(ProductsApiConfig())
    api.getProductByID(id).connect({
        Log.e("data", it.data1)
    }, 5)
}