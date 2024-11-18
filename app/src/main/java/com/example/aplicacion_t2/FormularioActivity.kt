package com.example.aplicacion_t2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicacion_t2.databinding.ActivityFormularioBinding
/**
 * Activity que hace referncia a un formulario de registro al no
 * tener una cuenta de sesion
 * */
class FormularioActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormularioBinding
    private lateinit var ficheroCompartido : SharedPreferences

    private var usos : Array<String>
    private lateinit var adapter : ArrayAdapter<String>
    private lateinit var adapterSpiner : ArrayAdapter<String>

    init {
        usos = Usos.arrayUsos
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_formulario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityFormularioBinding.inflate(layoutInflater) //inflamos el binding
        setContentView(binding.root) //carga el binding (el layout).root es el punto principal

        iniciarPreferenciasCompartidas()
        initEvent()
        initAdapter()

        binding.btnFloat.setOnClickListener {
            val nombre_usuario = binding.editText.text.toString()
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("usuario", nombre_usuario )
            }
            startActivity(intent)
        }
    }
    private fun iniciarPreferenciasCompartidas(){
        val nombreFicheroCompartido = getString(R.string.nombre_fichero_preferencia_compartida)
        this.ficheroCompartido = getSharedPreferences(nombreFicheroCompartido, MODE_PRIVATE)
    }
    private fun initAdapter() {
        adapter = ArrayAdapter (
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            usos
        )
        adapterSpiner = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
            ,
            usos
        )



        binding.spinner.adapter = adapterSpiner
    }
    private fun initEvent() {
        proveBtnFloat()
        proveCheck()
        proveCheck()
        proveRadio()
        proveSwitch()
        proveSpinner()
    }

    private fun proveRadio(){
        binding.group1.setOnCheckedChangeListener{
                group, chekedId ->
            when (chekedId){
                binding.rbHombre.id->{
                    showMsg("Sexo masculino ")
                }

                binding.rbMujer.id->{
                    showMsg("Sexo femenino")

                }
                binding.rbOtro.id->{
                    showMsg("Prefiero no decirlo")
                }
            }
        }
    }
    private fun proveCheck(){
        binding.cbUno.setOnCheckedChangeListener{
                btnView, isCheked ->
            if (isCheked){
                showMsg("Acepto que soy un pro")
            }else{
                showMsg("No acepto que soy un pro")
            }
        }

        binding.cbDos.setOnCheckedChangeListener{
                btnView, isCheked ->
            if (isCheked){
                showMsg("Acepto los terminos")
            }else{
                showMsg("No acepto los terminos")
            }
        }

    }
    private fun proveSwitch(){
        binding.switchBtn.setOnCheckedChangeListener {
                buttonView, isChecked ->
            if (isChecked){
                showMsg("Notificaciones activadas")
            }else{
                showMsg("Notificaciones desactivadas")
            }
        }
    }

    private fun proveSpinner(){
        binding.spinner.onItemSelectedListener =
            object :  AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                                  }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    showMsg("No selecciono nada")
                }
            }


    }


    private fun proveBtnFloat(){
        binding.btnFloat.setOnClickListener{
            showMsg(binding.editText.text.toString())
        }
        val event : View.OnClickListener
        event = object : View.OnClickListener{
            override fun onClick(v: View?) {
                showMsg("Click desde objeto anónimo del botón")            }
        }
        binding.btnFloat.setOnClickListener(event)
    }
    private fun showMsg(cad : String){
        Toast.makeText(this, cad, Toast.LENGTH_LONG).show()
    }
}