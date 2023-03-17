package com.example.cavzexameniib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class actualizarPapeleria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_papeleria)
        var idPapeleria = intent.extras?.getString("idPapeleria")?: "No encontrado"
        var nombrePapeleriaInt = intent.extras?.getString("nombrePapeleria")?: "No encontrado"
        var direccionInt = intent.extras?.getString("direccion")?: "No encontrado"
        var telefonoInt = intent.extras?.getInt("telefono")?: "No encontrado"
        var fundacion = intent.extras?.getString("fundacion")?: "No encontrado"

        //Seteo los datos en cada input
        var nombrePapeleria=findViewById<EditText>(R.id.input_act_nombre_pape)
        nombrePapeleria.setText(nombrePapeleriaInt)
        var direccion=findViewById<EditText>(R.id.input_act_direccion)
        direccion.setText(direccionInt)
        var telefono=findViewById<EditText>(R.id.input_act_telefono)
        telefono.setText(telefonoInt.toString())
        var fecha=findViewById<EditText>(R.id.input_act_fecha)
        fecha.setText(fundacion)

        val btn_actualizar=findViewById<Button>(R.id.btn_actualizar_papeleria)
        btn_actualizar.setOnClickListener {
            var funciones=BDPapeleriaHelper()
            val actualizacion = funciones.actualizarPapeleria(
                idPapeleria,
                nombrePapeleria.text.toString(),
                direccion.text.toString(),
                telefono.text.toString().toInt(),
                fecha.text.toString()
            )
            if (actualizacion > 0) {
                Toast.makeText(this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show()
                irActividad(MainActivity::class.java)
            } else {
                Toast.makeText(this, "ERROR EN ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}