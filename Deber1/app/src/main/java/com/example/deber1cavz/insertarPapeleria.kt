package com.example.deber1cavz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class insertarPapeleria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_papeleria)
        //Extraer datos de los input
        val nombre_papeleria = findViewById<EditText>(R.id.input_nombre_pape)
        val direccion = findViewById<EditText>(R.id.input_direccion)
        val telefono = findViewById<EditText>(R.id.input_telefono)
        val fecha = findViewById<EditText>(R.id.input_fecha)
        //Insertar datos en la BD
        val papeleria = BDPapeleriaHelper(this)
        val btnInsertar = findViewById<Button>(R.id.btn_insertar_papeleria)
        btnInsertar.setOnClickListener {
            val inserccion = papeleria.insertarPapeleria(
                nombre_papeleria.text.toString(),
                direccion.text.toString(),
                telefono.text.toString().toInt(),
                fecha.text.toString()
            )
            if (inserccion > 0) {
                Toast.makeText(this, "DATOS GUARDADOS", Toast.LENGTH_LONG).show()
                irActividad(MainActivity::class.java)
            } else {
                Toast.makeText(this, "ERROR EN INSERTAR DATOS", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}