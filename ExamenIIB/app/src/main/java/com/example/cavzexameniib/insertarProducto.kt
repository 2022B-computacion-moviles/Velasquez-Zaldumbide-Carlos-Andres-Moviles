package com.example.cavzexameniib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class insertarProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_producto)
        var idPapeleria = intent.extras?.getString("idPapeleria")?: "No encontrado"

        //Extraer datos de los input
        val nombre_producto = findViewById<EditText>(R.id.input_nombrePro)
        val stock = findViewById<EditText>(R.id.input_stock)
        val precio = findViewById<EditText>(R.id.input_precio)
        var disponibilidad:Boolean=true

        //Insertar datos en la BD
        var producto=BDPapeleriaHelper()
        val btnInsertar = findViewById<Button>(R.id.btn_insertar_producto)
        btnInsertar.setOnClickListener {
            //Se asigana disponibilidad false si stock es 0
            if(stock.text.toString().toInt()==0){
                disponibilidad=false
            }
            val inserccion = producto.insertarProducto(
                idPapeleria,
                nombre_producto.text.toString(),
                stock.text.toString().toInt(),
                precio.text.toString().toFloat(),
                disponibilidad
            )
            if (inserccion > 0) {
                Toast.makeText(this, "DATOS GUARDADOS", Toast.LENGTH_LONG).show()
                val intent = Intent(this@insertarProducto, verProducto::class.java)
                intent.putExtra("idPapeleria", idPapeleria)
                startActivity(intent)
            } else {
                Toast.makeText(this, "ERROR EN INSERTAR DATOS", Toast.LENGTH_LONG).show()
            }
        }

    }
}