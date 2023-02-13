package com.example.movcompcavz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class InsertarPapeleria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_papeleria)

        val botonCrearBDD = findViewById<Button>(R.id.btn_ingresar_papeleria)
        botonCrearBDD
            .setOnClickListener {
                val nombre_papeleria = findViewById<EditText>(R.id.input_nombre_pape)
                val direccion = findViewById<EditText>(R.id.input_direccion)
                val telefono = findViewById<EditText>(R.id.input_telefono)
                val fecha = findViewById<EditText>(R.id.input_fecha)
                BaseDeDatos.tablaPapeleria!!.crearPapeleria(
                    nombre_papeleria.text.toString(),
                    direccion.text.toString(),
                    telefono.text.toString().toInt(),
                    fecha.text.toString()
                )
            }
    }
}