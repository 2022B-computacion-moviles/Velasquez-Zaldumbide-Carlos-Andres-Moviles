package com.example.deber1cavz

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class verProducto : AppCompatActivity() {
    companion object{
        var idProductoSeleccionado = 0
        var idPapeleriaGlobal = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_producto)

        //Se obtinene el id de la papeleria seleccionada
        var idPapeleria = intent.extras?.getInt("idPapeleria")?: "No encontrado"
        idPapeleria=idPapeleria.toString().toInt()
        idPapeleriaGlobal=idPapeleria
        Log.d("esto es idpapeleria global", idPapeleriaGlobal.toString())
        //Funcion que muestra en el list view las papelerias
        verListaProductos(idPapeleria)

        //Boton que envia a la interfaz de crear una papeleria
        val botonInsertarProducto = findViewById<Button>(R.id.btn_crear_producto)
        botonInsertarProducto
            .setOnClickListener{
                val intent = Intent(this@verProducto, insertarProducto::class.java)
                intent.putExtra("idPapeleria", idPapeleria)
                startActivity(intent)
            }
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
        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ){
        super.onCreateContextMenu(menu, v, menuInfo)
        //LLenamos las opciones del menu
        val inflater=menuInflater
        inflater.inflate(R.menu.menuproducto, menu)
        //Obtener el id del ArrayListSeleccionada
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idProductoSeleccionado =id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val arreglo = BDPapeleriaHelper(this).verProductos(idPapeleriaGlobal)
        return when (item.itemId) {
            R.id.mpr_editar -> {
                val intent = Intent(this@verProducto, actualizarProducto::class.java)
                intent.putExtra("idProducto", arreglo[idProductoSeleccionado].id_producto)
                intent.putExtra("nombreProducto", arreglo[idProductoSeleccionado].nombre_producto)
                intent.putExtra("stock", arreglo[idProductoSeleccionado].stock)
                intent.putExtra("precio", arreglo[idProductoSeleccionado].precio)
                intent.putExtra("idPapeleria", arreglo[idProductoSeleccionado].idPapeleriaPro)
                startActivity(intent)
                return true
            }
            R.id.mpr_eliminar -> {
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Esta seguro de eliminar este producto?")
        builder.setPositiveButton(
            "ACEPTAR",
            DialogInterface.OnClickListener { dialog, which ->
                val producto= BDPapeleriaHelper(this)
                val arreglo = BDPapeleriaHelper(this).verProductos(idPapeleriaGlobal)
                var idEliminar=arreglo[idProductoSeleccionado].id_producto
                val resultado = producto.eliminarProducto(idEliminar)

                if (resultado > 0) {
                    Toast.makeText(this, "DATOS ELIMINADOs", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@verProducto, verProducto::class.java)
                    intent.putExtra("idPapeleria", idPapeleriaGlobal)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "ERROR AL ELIMINAR DATOS", Toast.LENGTH_LONG).show()
                }
            }
        )
        builder.setNegativeButton(
            "CANCELAR",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}