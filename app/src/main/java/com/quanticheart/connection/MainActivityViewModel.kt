package com.quanticheart.connection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quanticheart.conn.extentions.connect
import com.quanticheart.connection.endpoints.carsApi.getCarsApi
import com.quanticheart.connection.endpoints.entity.DataList

class MainActivityViewModel : ViewModel() {
    private val carsApi = getCarsApi(AppApplication.appContext)

    val loading = MutableLiveData<Boolean>()
    val data = MutableLiveData<DataList>()
    val error = MutableLiveData<Throwable>()

    fun getCarsList() {
        loading.value = true
        carsApi.getUserDataList().connect({
            data.value = it
            loading.value = false
        }, {
            error.value = it
            loading.value = false
        })
    }
}
