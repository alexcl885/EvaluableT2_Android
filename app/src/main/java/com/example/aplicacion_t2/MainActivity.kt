package com.example.aplicacion_t2

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.AlarmClock
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicacion_t2.databinding.ActivityMainBinding
import com.example.aplicacion_t2.databinding.ActivityPhoneCallBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var fichero_compartido: SharedPreferences
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mainBinding = ActivityMainBinding.inflate(layoutInflater) //inflamos el binding
        setContentView(mainBinding.root)

        iniciarPreferenciasCompartidas()
        initHandler()
        /**
         * Hacemos que al pulsar el telefono nos mande a otra activity
         */

        mainBinding.ivLlamada.setOnClickListener{view ->
            intent = Intent(this, ConfigPhone::class.java)
            startActivity(intent)
        }
        /**
         * Hacemos que al pulsar el boton de email nos mande a la pagina web de Email
         *  @param url es la URL donde se situa gmail
         * */
        mainBinding.ivAlarma.setOnClickListener{
            poner_alarma()
        }


        /**
         * Hacemos que al pulsar el boton de gitHub nos mande a la pagina web de GitHub
        *  @param url es la URL donde se situa gitHub
        * */
        mainBinding.ivGithub.setOnClickListener{ view ->
            val url = "https://github.com/"
            intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
        /**
         * Hacemos que al pulsar el boton del mapa nos mande a la pagina web de google maps
         *  @param url es la URL donde se situa maps
         * */
        mainBinding.ivMaps.setOnClickListener{view ->
            val url = "https://www.google.com/maps"
            intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
        /**
         * Hacemos que al pulsar el boton del dado nos mande al activity del juego de los dados.
         * */
        mainBinding.ivDados.setOnClickListener{
            val intent = Intent(this, DadosActivity::class.java)
            startActivity(intent)
        }
        /**
         * Hacemos que al pulsar el boton nos mande al activity de los chistes
         * */
        mainBinding.ivChistes.setOnClickListener{
            val intent = Intent(this, ChistesActivity::class.java)
            startActivity(intent)
        }
        mostrarUsuario()

    }
    /**
     * Metodo en el que se hace visible en los dos primeros segundos un progressBar y
     * los demas componentes se hacen invisibles, y despues de esos segundos
     * se hacen visibles los componentes y se hace invisible el progressBar
     * */
    private  fun initHandler(){
        handler = Handler(Looper.getMainLooper())
        mainBinding.pbEntradaMain.visibility = View.VISIBLE
        mainBinding.ivMaps.visibility = View.GONE
        mainBinding.ivDados.visibility = View.GONE
        mainBinding.ivChistes.visibility = View.GONE
        mainBinding.ivGithub.visibility = View.GONE
        mainBinding.ivLlamada.visibility = View.GONE
        mainBinding.ivImagen.visibility = View.GONE
        mainBinding.ivAlarma.visibility = View.GONE
        mainBinding.tvUsuario.visibility = View.GONE
        mainBinding.tvBienvenido.visibility = View.GONE
        Thread{
            Thread.sleep(2000)
            handler.post{
                mainBinding.ivAlarma.visibility = View.VISIBLE
                mainBinding.pbEntradaMain.visibility = View.VISIBLE
                mainBinding.ivMaps.visibility = View.VISIBLE
                mainBinding.ivDados.visibility = View.VISIBLE
                mainBinding.ivChistes.visibility = View.VISIBLE
                mainBinding.ivGithub.visibility = View.VISIBLE
                mainBinding.ivLlamada.visibility = View.VISIBLE
                mainBinding.ivImagen.visibility = View.VISIBLE
                mainBinding.tvUsuario.visibility = View.VISIBLE
                mainBinding.tvBienvenido.visibility = View.VISIBLE
                mainBinding.pbEntradaMain.visibility = View.GONE
            }
        }.start()
    }
    /**
     * Metodo que pone una alarma
     * */
    private fun poner_alarma(){
        val intent_alarm = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, "alarma")
            putExtra(AlarmClock.EXTRA_MINUTES, 2)
        }
        startActivity(intent_alarm)
    }
    /**Inicializamos las preferencias compartidas*/
    private fun iniciarPreferenciasCompartidas() {
        val nombreFicheroCompartido = getString(R.string.nombre_fichero_preferencia_compartida)

        this.fichero_compartido = getSharedPreferences(nombreFicheroCompartido, MODE_PRIVATE)

    }
    private fun mostrarUsuario(){
        val user = intent.getStringExtra("usuario")
        mainBinding.tvUsuario.text = user


    }




}