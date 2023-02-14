package com.example.deber1cavz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class verProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_producto)

        //Se obtinene el id de la papeleria seleccionada
        var idPapeleria = intent.extras?.getInt("idPapeleria")?: "No encontrado"
        idPapeleria=idPapeleria.toString().toInt()
        //Funcion que muestra en el list view las papelerias
        verListaProductos(idPapeleria)
    }

    fun verListaProductos(id:Int) {
        val productos = BDPapeleriaHelper(this).verProductos(id)
        val listView = findViewById<ListView>(R.id.lv_list_view_pro)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            productos
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        //registerForContextMenu(listView)
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}