package com.example.aplicacion_t2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicacion_t2.databinding.ActivityLoginBinding

/**
 * Activity que hace referencia a un login sencillo
 * @author Alejandro Copado Lopez
 * */
class Login : AppCompatActivity() {
    private lateinit var loginBinding : ActivityLoginBinding
    private lateinit var fichero_compartido : SharedPreferences
    private lateinit var intent : Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        iniciarPreferenciasCompartidas()


        loginBinding.btEntrar.setOnClickListener{
            /**Constantes para entrar a la aplicacion*/
            val USER = "copado"
            val PASSWORD = "copado"
            /**Inputs de los campos de texto*/
            val usuario = loginBinding.email.text.toString()
            val contasena = loginBinding.comtrasena.text.toString()

            /**
             * Logica por el cual si esta en blanco o con espacios (Black) manda un mensaje de error.
             * Si los inputs son igual a las constantes entrara al activity principal.
             * Y si los datos no son ciertos pues se mostrara por pantalla que estan mal.
             * */
            if (usuario.isBlank() || contasena.isBlank()) {
                Toast.makeText(this, "Usuario o contraseña vacíos", Toast.LENGTH_LONG).show()
            } else if (usuario == USER && contasena == PASSWORD) {
                intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("usuario",usuario)
                    putExtra("contrasena", contasena)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos, registrese si no tiene cuenta", Toast.LENGTH_LONG).show()
            }

        }
        loginBinding.btRegistrarse.setOnClickListener{
            val intent = Intent(this, FormularioActivity::class.java)
            startActivity(intent)
        }
    }
    /**
     * Metodo que inicia las preferencias compartidas
     * */
    private fun iniciarPreferenciasCompartidas(){
        val nombreFicheroCompartido = getString(R.string.nombre_fichero_preferencia_compartida)

        this.fichero_compartido = getSharedPreferences(nombreFicheroCompartido, MODE_PRIVATE)
    }

}