package com.example.aplicacion_t2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.util.Log

import android.view.View

import com.example.aplicacion_t2.databinding.ActivityChistesBinding

import java.util.Locale
import kotlin.random.Random

class ChistesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityChistesBinding
    private lateinit var textToSpeech: TextToSpeech  //descriptor de voz
    private val TOUCH_MAX_TIME = 500 // en milisegundos
    private var touchLastTime: Long = 0  //para saber el tiempo entre toque.

    private lateinit var handler: Handler

    val MYTAG = "LOGCAT"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChistesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureTextToSpeech()
        initEvent()
        //codigo para volver al activity principal
        binding.btAtras.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun initHander(tiempo:Long) {
        handler = Handler(Looper.getMainLooper())  //queremos que el tema de la IU, la llevemos al hilo principal.
        binding.progressBar.visibility = View.VISIBLE  //hacemos visible el progress
        binding.btnExample.visibility = View.GONE  //ocultamos el botón.
        binding.btAtras.visibility = View.GONE

        Thread{
            Thread.sleep(tiempo)
            handler.post{
                binding.progressBar.visibility = View.GONE  //ocultamos el progress
                Log.i(MYTAG,"Se ejecuta correctamente el hilo")
                binding.btnExample.visibility = View.VISIBLE
                binding.btAtras.visibility = View.VISIBLE

            }
        }.start()
    }
    private fun configureTextToSpeech() {
        textToSpeech = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
            if(it != TextToSpeech.ERROR){
                textToSpeech.language = Locale.getDefault()
                // textToSpeech.setSpeechRate(1.0f)
                Log.i(MYTAG,"Sin problemas en la configuración TextToSpeech")
            }else{
                Log.i(MYTAG,"Error en la configuración TextToSpeech")
            }
        })
    }
    private fun initEvent() {

        binding.btnExample.setOnClickListener{
            /**
             * Genero una lista de chistes que al pulsar dos veces
             * genera un numero random y elige un chiste
             * */
            val lista_chistes = listOf(
                "El pasado, el presente y el futuro entran en un bar. Fue tenso.",
                "—Doctor, ¿cómo está mi hermano? —Está en coma. —¡Menos mal, siempre ha sido muy puntual!",
                "\"¿Me da un café con leche corto?\". \"Se ha roto la máquina, cambio\"",
                "Ayer le eché agua al café para que estuviera más despierto.",
                "¿Cuál es el colmo de Aladdín? Tener mal genio.",
                "¿Por qué los pájaros no usan Facebook? Porque ya tienen Twitter.",
                "¿Qué hace un mudo bailando? Una mudanza.",
                "Hoy no tengo WiFi, pero siento una conexión contigo.",
                "¿Qué hace una abeja en el gimnasio? ¡Zum-ba!",
                "¿Qué le dice una impresora a otra? Esta hoja es tuya o es impresión mía."
            )

            val num_aleatorio = Random.nextInt(lista_chistes.size)
            val chiste_random = lista_chistes[num_aleatorio];


            //Sacamos el tiempo actual
            val currentTime = System.currentTimeMillis()
            //Comprobamos si el margen entre pulsación, da lugar a una doble pulsación.
            if (currentTime - touchLastTime < TOUCH_MAX_TIME){
                //  touchNumber=0
                executorDoubleTouch(chiste_random)//hemos pulsado dos veces, por tanto lanzamos el chiste.

                // Calcular la duración aproximada del chiste para ejecutar el progressbar por detras
                val palabras = chiste_random.split(" ").size // cuento las palabras
                val numero_aprox = (palabras * 500).toLong() //mas o menos pienso que por palabra son medio segundo
                initHander(numero_aprox) //mientras cuenta el chiste se muestra el progressbar
                Log.i(MYTAG,"Escuchamos el chiste")
            }
            else{
                //  touchNumber++
                Log.i(MYTAG,"Hemos pulsado 1 vez.")
                //Describimos el botón, 1 sóla pulsación
                speakMeDescription("Toca dos veces para escuchar un chiste")
            }

            touchLastTime = currentTime


        }
    }
    private fun speakMeDescription(s: String) {
        Log.i(MYTAG,"Intenta hablar")
        textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun executorDoubleTouch(chiste: String) {
        speakMeDescription(chiste)

    }

    override fun onDestroy() {
        //Si hemos inicializado la propiedad textToSpeech, es porque existe.
        if (::textToSpeech.isInitialized){
            textToSpeech.stop()
            textToSpeech.shutdown()

        }
        super.onDestroy()
    }
}