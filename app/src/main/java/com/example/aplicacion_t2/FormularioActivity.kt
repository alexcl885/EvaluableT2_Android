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

class FormularioActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormularioBinding
    private lateinit var ficheroCompartido : SharedPreferences

    private var alumns : Array<String>
    private lateinit var adapter : ArrayAdapter<String>
    private lateinit var adapterSpiner : ArrayAdapter<String>

    init {
        alumns = Usos.arrayAlum
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
            val intent = Intent(this, MainActivity::class.java)
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
            alumns
        )
        adapterSpiner = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
            ,
            alumns
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
        proveToggle()
        proveButtonActionEditor()
    }
    private fun proveButtonActionEditor() {
        binding.editText.setOnEditorActionListener{
                v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND){
                showMsg("Clase, capturo el evento del Action del teclado")
                //Trozo de código, que Cierra el teclado después de pulsar el botón de Acción.
                //copiar y pegar
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                true
            }else
                false
        }


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
                showMsg("Boton Switch activado")
            }else{
                showMsg("Boton Switch Desactivado")
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
    private fun proveToggle(){
        binding.toggBtn.setOnCheckedChangeListener {
                buttonView, isChecked ->
            if (isChecked){
                showMsg("Activado")
            }else{
                showMsg("Desactivado")
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