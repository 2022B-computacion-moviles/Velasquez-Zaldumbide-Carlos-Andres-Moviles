package com.example.cavzexameniib

import android.content.ContentValues
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
import com.google.firebase.firestore.FirebaseFirestore

class verProducto : AppCompatActivity() {
    companion object{
        var idProductoSeleccionado = 0
        var idPapeleriaGlobal = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_producto)
        //Se obtinene el id de la papeleria seleccionada
        var idPapeleria = intent.extras?.getString("idPapeleria")?: "No encontrado"
        idPapeleriaGlobal=idPapeleria
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

    fun verListaProductos(id:String) {
        val db = FirebaseFirestore.getInstance()
        val salida=ArrayList<BDProducto>()
        val papelerias = db.collection("papeleria")
        val papeleriaEscogido = papelerias.document(id)
        val productos=papeleriaEscogido.collection("producto")
        var productosEncontrados=BDProducto()
        productos.get().addOnSuccessListener{result ->
                for (document in result) {
                    productosEncontrados.id_producto=document.id
                    productosEncontrados.nombre_producto=document.get("nombre_producto").toString()
                    productosEncontrados.stock=document.get("stock").toString().toInt()
                    productosEncontrados.precio=document.get("precio").toString().toFloat()
                    productosEncontrados.disponible=document.get("disponible").toString().toBoolean()
                    salida.add(productosEncontrados)
                    productosEncontrados=BDProducto()
                }
                val listView = findViewById<ListView>(R.id.lv_list_view_pro)
                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    salida
                )
                listView.adapter = adaptador
                adaptador.notifyDataSetChanged()
                registerForContextMenu(listView)
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error al obtener producto: ", exception)
            }
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
        val db = FirebaseFirestore.getInstance()
        val papelerias = db.collection("papeleria")
        val papeleriaEscogido = papelerias.document(idPapeleriaGlobal)
        val productos=papeleriaEscogido.collection("producto")
        var productosEncontrados=BDProducto()
        val arreglo=ArrayList<BDProducto>()

        return when (item.itemId) {
            R.id.mpr_editar -> {
                productos.get().addOnSuccessListener { result ->
                        for (document in result) {
                            productosEncontrados.id_producto=document.id
                            productosEncontrados.nombre_producto=document.get("nombre_producto").toString()
                            productosEncontrados.stock=document.get("stock").toString().toInt()
                            productosEncontrados.precio=document.get("precio").toString().toFloat()
                            productosEncontrados.disponible=document.get("disponible").toString().toBoolean()
                            arreglo.add(productosEncontrados)
                            productosEncontrados=BDProducto()
                        }
                        val intent = Intent(this@verProducto, actualizarProducto::class.java)
                        intent.putExtra("idProducto", arreglo.get(idProductoSeleccionado).id_producto)
                        intent.putExtra("nombreProducto", arreglo.get(idProductoSeleccionado).nombre_producto)
                        intent.putExtra("stock", arreglo.get(idProductoSeleccionado).stock)
                        intent.putExtra("precio", arreglo.get(idProductoSeleccionado).precio)
                        intent.putExtra("idPapeleria", idPapeleriaGlobal)
                        startActivity(intent)
                    }
                    .addOnFailureListener { exception ->
                        Log.d(ContentValues.TAG, "Error al obtener papeleria: ", exception)
                    }
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
        val db = FirebaseFirestore.getInstance()
        val papelerias = db.collection("papeleria")
        val papeleriaEscogido = papelerias.document(idPapeleriaGlobal)
        val productos=papeleriaEscogido.collection("producto")
        var productosEncontrados=BDProducto()
        val arreglo=ArrayList<BDProducto>()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Esta seguro de eliminar este producto?")
        builder.setPositiveButton(
            "ACEPTAR",
            DialogInterface.OnClickListener { dialog, which ->
                productos.get().addOnSuccessListener { result ->
                    for (document in result) {
                        productosEncontrados.id_producto=document.id
                        arreglo.add(productosEncontrados)
                        productosEncontrados=BDProducto()
                    }
                    var funciones=BDPapeleriaHelper()
                    var idEliminar=arreglo.get(idProductoSeleccionado).id_producto
                    val resultado = funciones.eliminarProducto(idPapeleriaGlobal,idEliminar)

                    if (resultado > 0) {
                        Toast.makeText(this, "DATOS ELIMINADOs", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@verProducto, verProducto::class.java)
                        intent.putExtra("idPapeleria", idPapeleriaGlobal)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "ERROR AL ELIMINAR DATOS", Toast.LENGTH_LONG).show()
                    }
                }
                    .addOnFailureListener { exception ->
                        Log.d(ContentValues.TAG, "Error al obtener papeleria: ", exception)
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