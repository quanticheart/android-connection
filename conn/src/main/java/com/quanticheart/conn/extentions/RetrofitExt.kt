package com.quanticheart.conn.extentions

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.conn(callback: ((T) -> Unit), error: ((Throwable) -> Unit)? = null) {
    this.enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            error?.let { it(t) }
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            response.body()?.let {
                callback(it)
            } ?: run { error(Throwable("null")) }
        }
    })
}

fun <T> Call<T>.conn(callback: ((T) -> Unit)) {
    this.enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            response.body()?.let {
                callback(it)
            }
        }
    })
}