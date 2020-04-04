package com.quanticheart.connection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quanticheart.connection.api.config.Api

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Api.test()
    }
}
