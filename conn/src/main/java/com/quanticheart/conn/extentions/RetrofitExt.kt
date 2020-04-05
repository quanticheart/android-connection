package com.quanticheart.conn.extentions

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.quanticheart.conn.base.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection

/**
 * Conn Genetic functions
 */
inline fun <reified T> Call<T>.conn(
    crossinline success: (T) -> Unit,
    crossinline error: (Throwable) -> Unit
) {
    this.enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            error(t.responseError())
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            response.responseSuccess(success, error)
        }
    })
}

inline fun <reified T> Call<T>.conn(crossinline callback: ((T) -> Unit)) {
    this.enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
//            response.responseSuccess()?.let {
//                callback(it)
//            }
        }
    })
}

/**
 * Response
 */

/**
 * Error
 */
fun Throwable?.responseError(msg: String? = null): Throwable {
    return when (this) {
        is HttpException -> {
            // Kotlin will smart cast at this point
            val errorJsonString = this.response().errorBody()?.string()
            val message = JsonParser().parse(errorJsonString)
                .asJsonObject["message"]
                .asString

            Log.e("HTTP ERROR", message)

            when (this.code()) {
                HttpsURLConnection.HTTP_UNAUTHORIZED -> Throwable("Unauthorised User ")
                HttpsURLConnection.HTTP_FORBIDDEN -> Throwable("Forbidden")
                HttpsURLConnection.HTTP_INTERNAL_ERROR -> Throwable("Internal Server Error")
                HttpsURLConnection.HTTP_BAD_REQUEST -> Throwable("Bad Request")
                HttpsURLConnection.HTTP_NOT_FOUND -> Throwable("Not Found")
                else -> Throwable(this.getLocalizedMessage())
            }
        }
        is JsonSyntaxException -> {
            Throwable("Something Went Wrong API is not responding properly!")
        }
        is SocketTimeoutException -> {
            Throwable("Connection Timeout")
        }
        is UnknownHostException->{
            Throwable("No connection")
        }
        is IOException -> {
            Throwable("Timeout")
        }
        is JsonSyntaxException -> {
            Throwable("Timeout")
        }
        else -> msg?.let { Throwable(msg) } ?: run { Throwable("Error this") }
    }
}

/**
 * Success
 */

inline fun <reified T> Response<T>.responseSuccess(
    crossinline success: (T) -> Unit,
    crossinline error: (Throwable) -> Unit
) {

    if (body() is BaseResponse<*>) {
        (body() as BaseResponse<*>).httpCode = code().toString()
    }

    when {
        isSuccessful -> {
            body()?.let {
                success(it)
            } ?: run {
                error(Throwable("Response is null"))
            }
        }
        code() == 404 -> error(Throwable("Api Not Found"))
        code() >= 400 -> {
            errorBody()?.let {
                try {
                    val r = GsonBuilder().create().fromJson(it.toString(), T::class.java)
                    success(r)
                } catch (e: java.lang.Exception) {
                    error(e.responseError())
                }
            } ?: run {
                error(Throwable("Response is null"))
            }
        }
        else -> {
            error(Throwable("Uncknown error"))
        }
    }
}