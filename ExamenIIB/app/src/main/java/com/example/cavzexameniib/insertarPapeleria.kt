package com.example.cavzexameniib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class insertarPapeleria : AppCompatActivity() {

    private val db= FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_papeleria)

        //Extraer datos de los input
        val nombre_papeleria = findViewById<EditText>(R.id.input_nombre_pape)
        val direccion = findViewById<EditText>(R.id.input_direccion)
        val telefono = findViewById<EditText>(R.id.input_telefono)
        val fecha = findViewById<EditText>(R.id.input_fecha)
        //Insertar datos en la BD
        val btnInsertar = findViewById<Button>(R.id.btn_insertar_papeleria)
        btnInsertar.setOnClickListener {
            val inserccion = this.insertarPapeleria(
                nombre_papeleria.text.toString(),
                direccion.text.toString(),
                telefono.text.toString().toInt(),
                fecha.text.toString()
            )
            if (inserccion > 0) {
                Toast.makeText(this, "DATOS GUARDADOS", Toast.LENGTH_LONG).show()
                irActividad(MainActivity::class.java)
            }
        }
    }
    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun insertarPapeleria(
        nombre_papeleria: String,
        direccion: String,
        telefono: Int?,
        fecha_fundacion: String,
    ):Int {
        var funciones=BDPapeleriaHelper()
        return funciones.insertarPapeleria(nombre_papeleria,direccion,telefono,fecha_fundacion)
    }
}