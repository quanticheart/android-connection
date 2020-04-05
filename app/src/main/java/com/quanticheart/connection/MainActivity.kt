package com.quanticheart.connection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.quanticheart.conn.extentions.connect
import com.quanticheart.connection.endpoints.carsApi.CarsApi
import com.quanticheart.connection.endpoints.carsApi.getCarsApi
import com.quanticheart.connection.endpoints.carsApi.getCarsApi3
import com.quanticheart.connection.endpoints.carsApi.getCarsApi4
import com.quanticheart.connection.endpoints.productsApi.getProductList2
import com.quanticheart.connection.endpoints.userApi.getUsersList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getUsersList()

        getProductList2()

        //Or

        val cars = getCarsApi()
        cars.getUserDataList().connect {

        }

        //Or

        val cars2 = CarsApi(this)
        cars2.api.getUserDataList().connect {

        }

        //Or

        getCarsApi3().getUserDataList().connect {

        }

        //Or

        getCarsApi4().getUserByID("1234").connect {

        }

        val viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        viewModel.loading.observe(this, Observer {
            if (it) "Show Loading" else "Hide Loading"
        })

        viewModel.data.observe(this, Observer {

        })

        viewModel.error.observe(this, Observer {

        })

        viewModel.getCarsList()
    }
}
