package com.example.examencafeteria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.ui.AppBarConfiguration
import com.example.examencafeteria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ingresar = findViewById<Button>(R.id.btn_ingresar)
        ingresar
            .setOnClickListener{
                irActividad(CafeteriaActivity::class.java)
            }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)

        startActivity (intent)
    }
}