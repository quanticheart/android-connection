package com.quanticheart.conn.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class BaseResponse<T> : Serializable {
    /**
     * some msg about connection
     *
     * @sample = connection ok
     * @sample = this user's password is incorrect
     */
    @SerializedName("msg")
    @Expose
    var msg: String = ""

    /**
     * status about connection
     *
     * @sample = false(this user's password is incorrect)
     * @sample = true(user's login success)
     */
    @SerializedName("success")
    @Expose
    var success: Boolean = false

    /**
     * status about connection
     *
     * @sample = false(this user's password is incorrect)
     * @sample = true(user's login success)
     */
    @SerializedName("status")
    @Expose
    var status: Boolean = false

    /**
     * code connection
     *
     * @sample = 200(connection ok)
     * @sample = 404(page not found)
     */
    @SerializedName("code")
    @Expose
    var httpCode: String = ""

    /**
     *  interface for entity's data
     *
     * @sample = data{ userdata{ name:"Jonh Alves" , credits:2000 } }
     *
     */
    @SerializedName("data")
    @Expose
    var response: T? = null
}