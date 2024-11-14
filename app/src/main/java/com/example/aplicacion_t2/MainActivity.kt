package com.example.aplicacion_t2

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
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




}