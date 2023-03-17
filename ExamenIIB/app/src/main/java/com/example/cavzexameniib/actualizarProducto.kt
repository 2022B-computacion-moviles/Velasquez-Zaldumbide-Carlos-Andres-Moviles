package com.example.cavzexameniib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class actualizarProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_producto)

        //Obtengo los datos de producto seleccionada para actualziar
        var idPapeleria = intent.extras?.getString("idPapeleria")?: "No encontrado"
        var idProductoInt = intent.extras?.getString("idProducto")?: "No encontrado"
        var nombreProductoInt = intent.extras?.getString("nombreProducto")?: "No encontrado"
        var stockInt = intent.extras?.getInt("stock")?: "No encontrado"
        var precioInt = intent.extras?.getFloat("precio")?: "No encontrado"
        var disponibilidad:Boolean=true

        //Seteo los datos en cada input
        var nombreProducto=findViewById<EditText>(R.id.input_act_nombre_pro)
        nombreProducto.setText(nombreProductoInt)
        var stock=findViewById<EditText>(R.id.input_act_stock)
        stock.setText(stockInt.toString())
        var precio=findViewById<EditText>(R.id.input_act_precio)
        precio.setText(precioInt.toString())

        val btn_actualizar=findViewById<Button>(R.id.btn_actualizar_producto)
        btn_actualizar.setOnClickListener {
            if(stock.text.toString().toInt()==0){
                disponibilidad=false
            }
            var funciones=BDPapeleriaHelper()
            val actualizacion = funciones.actualizarProducto(
                idProductoInt,
                idPapeleria,
                nombreProducto.text.toString(),
                stock.text.toString().toInt(),
                precio.text.toString().toFloat(),
                disponibilidad
            )

            if (actualizacion > 0) {
                Toast.makeText(this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show()
                val intent = Intent(this@actualizarProducto, verProducto::class.java)
                intent.putExtra("idPapeleria", idPapeleria)
                startActivity(intent)
            } else {
                Toast.makeText(this, "ERROR EN ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }
}