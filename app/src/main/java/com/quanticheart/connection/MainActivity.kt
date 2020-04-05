package com.quanticheart.connection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.quanticheart.connection.api.endpoints.config.getPokemonList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPokemonList()
        reload.setOnClickListener {
            getPokemonList()
        }
    }
}
