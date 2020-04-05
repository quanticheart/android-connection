package com.quanticheart.connection.endpoints.carsApi

import android.content.Context
import androidx.lifecycle.ViewModel
import com.quanticheart.conn.client.RetrofitCreate
import com.quanticheart.connection.endpoints.userApi.UserApiConfig

fun Context.getCarsApi(): CarsEndPoints = RetrofitCreate.createConn(UserApiConfig())

class CarsApi(context: Context) {
    var api: CarsEndPoints

    init {
        val header = UserApiConfig()
        header.addHeader("User-Api-Token", "fklsdflkehfuwehfiuehfjkwhfjksdhfebfjhwg")
        api = RetrofitCreate.createConn(header)
    }
}

class CarsApi2(context: Context) {
    var api: CarsEndPoints = RetrofitCreate.createConn(CarsApiConfig(context))
}

fun Context.getCarsApi3(): CarsEndPoints = RetrofitCreate.createConn(CarsApiConfig(this))

fun Context.getCarsApi4() = RetrofitCreate.createConn<CarsEndPoints>(CarsApiConfig(this))

fun ViewModel.getCarsApi(context: Context): CarsEndPoints =
    RetrofitCreate.createConn(CarsApiConfig(context))

