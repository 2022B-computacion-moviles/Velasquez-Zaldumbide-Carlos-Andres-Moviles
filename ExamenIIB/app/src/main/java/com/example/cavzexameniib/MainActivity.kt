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
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    companion object{
        var idPapeleriaSeleccionado = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Funcion que muestra en el list view las papelerias
        verListaPapelerias()

        //Boton que envia a la interfaz de crear una papeleria
        val botonInsertarEntrenador = findViewById<Button>(R.id.btn_crear_papeleria)
        botonInsertarEntrenador
            .setOnClickListener{
                irActividad(insertarPapeleria::class.java)
            }
    }

    fun verListaPapelerias() {
        val db = FirebaseFirestore.getInstance()
        val salida=ArrayList<BDPapeleria>()
        var papeleriasEncontradas=BDPapeleria()
        db.collection("papeleria")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    papeleriasEncontradas.id_papeleria=document.id
                    papeleriasEncontradas.nombre_papeleria=document.get("nombre_papeleria").toString()
                    papeleriasEncontradas.direccion=document.get("direccion").toString()
                    papeleriasEncontradas.telefono=document.get("telefono").toString().toInt()
                    papeleriasEncontradas.fecha_fundacion=document.get("fecha_fundacion").toString()
                    salida.add(papeleriasEncontradas)
                    papeleriasEncontradas=BDPapeleria()
                }
                val listView = findViewById<ListView>(R.id.lv_list_view)
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
                Log.d(ContentValues.TAG, "Error al obtener papeleria: ", exception)
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
        inflater.inflate(R.menu.menupapeleria, menu)
        //Obtener el id del ArrayListSeleccionada
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idPapeleriaSeleccionado =id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val db = FirebaseFirestore.getInstance()
        val arreglo=ArrayList<BDPapeleria>()
        var papeleriasEncontradas=BDPapeleria()
        //val arreglo = BDPapeleriaHelper(this).verPapelerias()
        return when (item.itemId) {
            R.id.mp_editar -> {
                db.collection("papeleria")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            papeleriasEncontradas.id_papeleria=document.id
                            papeleriasEncontradas.nombre_papeleria=document.get("nombre_papeleria").toString()
                            papeleriasEncontradas.direccion=document.get("direccion").toString()
                            papeleriasEncontradas.telefono=document.get("telefono").toString().toInt()
                            papeleriasEncontradas.fecha_fundacion=document.get("fecha_fundacion").toString()
                            arreglo.add(papeleriasEncontradas)
                            papeleriasEncontradas=BDPapeleria()
                        }
                        val intent = Intent(this@MainActivity, actualizarPapeleria::class.java)
                        intent.putExtra("idPapeleria",arreglo.get(idPapeleriaSeleccionado).id_papeleria)
                        intent.putExtra("nombrePapeleria", arreglo.get(idPapeleriaSeleccionado).nombre_papeleria)
                        intent.putExtra("direccion", arreglo.get(idPapeleriaSeleccionado).direccion)
                        intent.putExtra("telefono", arreglo.get(idPapeleriaSeleccionado).telefono)
                        intent.putExtra("fundacion", arreglo.get(idPapeleriaSeleccionado).fecha_fundacion)
                        startActivity(intent)
                    }
                    .addOnFailureListener { exception ->
                        Log.d(ContentValues.TAG, "Error al obtener papeleria: ", exception)
                    }
                return true
            }
            R.id.mp_eliminar -> {
                abrirDialogo()
                return true
            }
            R.id.mp_Ver -> {
                val intent = Intent(this@MainActivity, verProducto::class.java)
                db.collection("papeleria")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            papeleriasEncontradas.id_papeleria=document.id
                            arreglo.add(papeleriasEncontradas)
                            papeleriasEncontradas=BDPapeleria()
                        }
                        intent.putExtra("idPapeleria", arreglo.get(idPapeleriaSeleccionado).id_papeleria)
                        startActivity(intent)
                    }
                    .addOnFailureListener { exception ->
                        Log.d(ContentValues.TAG, "Error al obtener papeleria: ", exception)
                    }
                //intent.putExtra("idPapeleria", arreglo[idPapeleriaSeleccionado].id_papeleria)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo() {
        val db = FirebaseFirestore.getInstance()
        val arreglo=ArrayList<BDPapeleria>()
        var papeleriasEncontradas=BDPapeleria()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Esta seguro de eliminar esta papeleria?")
        builder.setPositiveButton(
            "ACEPTAR",
            DialogInterface.OnClickListener { dialog, which ->
                db.collection("papeleria")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            papeleriasEncontradas.id_papeleria=document.id
                            arreglo.add(papeleriasEncontradas)
                            papeleriasEncontradas=BDPapeleria()
                        }
                        var funciones=BDPapeleriaHelper()
                        var idEliminar=arreglo.get(idPapeleriaSeleccionado).id_papeleria
                        val resultado=funciones.eliminarPapeleria(idEliminar)
                        if (resultado > 0) {
                            Toast.makeText(this, "DATOS ELIMINADOs", Toast.LENGTH_LONG).show()
                            irActividad(MainActivity::class.java)
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