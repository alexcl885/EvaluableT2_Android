package com.example.aplicacion_t2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicacion_t2.databinding.ActivityInstruccionesDadosBinding
/**
 * Activity que muestra el manual de instrucciones del juego de los dados
 * */
class instrucciones_dados : AppCompatActivity() {
    private lateinit var binding : ActivityInstruccionesDadosBinding
    private lateinit var fichero_compartido : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_instrucciones_dados)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicarPreferenciasCompartidas()
        binding = ActivityInstruccionesDadosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btAtras.setOnClickListener {
            val intent = Intent(this, DadosActivity::class.java)
            startActivity(intent)
        }
    }
    private fun inicarPreferenciasCompartidas(){
        val nombreFicheroCompartido = getString(R.string.nombre_fichero_preferencia_compartida)
        this.fichero_compartido = getSharedPreferences(nombreFicheroCompartido, MODE_PRIVATE)
    }
}