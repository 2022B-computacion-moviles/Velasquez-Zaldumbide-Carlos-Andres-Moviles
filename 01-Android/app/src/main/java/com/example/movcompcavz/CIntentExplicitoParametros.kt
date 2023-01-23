package com.example.movcompcavz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)
        val nombre=intent.getStringArrayExtra("nombre")
        val apellido=intent.getStringArrayExtra("apellido")
        val edad=intent.getStringArrayExtra("edad")
        val entrenador=intent.getParcelableExtra<BEntrenador>(
            "entrenador"
        )
        val boton = findViewById<Button>(R.id.btn_devolver_respuesta)
        boton
            .setOnClickListener { devolverRespuesta() }
    }

    fun devolverRespuesta(){
        val intentDevolverParametros=Intent()
        intentDevolverParametros.putExtra("nombreModificado","Andres")
        intentDevolverParametros.putExtra("edadModificado",32)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }
}