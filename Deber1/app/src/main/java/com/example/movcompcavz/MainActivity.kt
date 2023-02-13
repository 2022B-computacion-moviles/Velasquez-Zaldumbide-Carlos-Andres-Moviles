package com.example.movcompcavz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verPapeleria()

        val botonInsertarEntrenador = findViewById<Button>(R.id.btn_crear_papeleria)
        botonInsertarEntrenador
            .setOnClickListener{
                irActividad(InsertarPapeleria::class.java)
            }
    }
    fun verPapeleria() {
        val papeleria = ESqliteHelperPapeleria(contexto = null)
        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            papeleria.verPapelerias()
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

}