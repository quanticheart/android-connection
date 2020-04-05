package com.quanticheart.connection.endpoints.userApi

import android.app.Activity
import android.util.Log
import com.quanticheart.conn.client.RetrofitCreate
import com.quanticheart.conn.extentions.connect

fun Activity.getUsersList() {
    val config = UserApiConfig(this)
    config.header["Add-after"] = "Added Success"
    config.header.put("Add-after-2", "Added Success")
    config.addHeader("Add-after-3", "Added Success")

    val api = RetrofitCreate.createConn<UserEndPoints>(config)
    api.getUserDataList().connect({
        Log.e("List Data", it.results.toString())
    }, {
        Log.e("ERROR", it.message)
    })
}

fun Activity.getUserByID(id: String) {
    val api = RetrofitCreate.createConn<UserEndPoints>(UserApiConfig())
    api.getUserByID(id).connect {
        Log.e("data", it.data1.toString())
    }
}