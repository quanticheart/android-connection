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

const val retryDefault = 2

/**
 * Conn Genetic functions
 */
inline fun <reified T> Call<T>.conn(
    crossinline success: (T) -> Unit,
    noinline error: (Throwable) -> Unit,
    retry: Int = retryDefault
) {
    this.enqueueWithRetry(success, error, retry)
}

inline fun <reified T> Call<T>.conn(
    crossinline success: ((T) -> Unit),
    retry: Int = retryDefault
) {
    this.enqueueWithRetry(success, null, retry)
}

inline fun <reified T> Call<T>.conn(
    crossinline success: ((T) -> Unit)
) {
    this.enqueueWithRetry(success, null, retryDefault)
}

/**
 * Retry connection
 */
inline fun <reified T> Call<T>.enqueueWithRetry(
    crossinline success: (T) -> Unit,
    noinline fail: ((Throwable) -> Unit)? = null,
    retry: Int = retryDefault
) {
    var countRetry = 1
    this.enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            if (countRetry <= retry) {
                Log.w("Connection fail", "Retry Connection - $countRetry")
                this@enqueueWithRetry.clone().enqueue(this)
                countRetry++
            } else {
                fail?.invoke(t.responseError())
            }
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            response.responseSuccess(success, fail)
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
            Throwable("Response is error (Call Application support)")
        }
        is SocketTimeoutException -> {
            Throwable("Connection Timeout")
        }
        is UnknownHostException -> {
            Throwable("No connection")
        }
        is IOException -> {
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
    noinline fail: ((Throwable) -> Unit)? = null
) {

    if (body() is BaseResponse<*>) {
        (body() as BaseResponse<*>).httpCode = code().toString()
    }

    when {
        isSuccessful -> {
            body()?.let {
                success(it)
            } ?: run {
                fail?.let { it(Throwable("Response is null")) }
            }
        }
        code() == 404 -> fail?.invoke(Throwable("Api Not Found"))
        code() >= 400 -> {
            errorBody()?.let {
                try {
                    val r = GsonBuilder().create().fromJson(it.toString(), T::class.java)
                    success(r)
                } catch (e: java.lang.Exception) {
                    fail?.invoke(e.responseError())
                }
            } ?: run {
                fail?.invoke(Throwable("Response is null"))
            }
        }
        else -> {
            fail?.invoke(Throwable("Uncknown error"))
        }
    }
}