package com.example.deber1cavz

import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

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
        val papelerias = BDPapeleriaHelper(this).verPapelerias()
        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            papelerias
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
        inflater.inflate(R.menu.menupapeleria, menu)
        //Obtener el id del ArrayListSeleccionada
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idPapeleriaSeleccionado =id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val arreglo = BDPapeleriaHelper(this).verPapelerias()
        return when (item.itemId) {
            R.id.mp_editar -> {
                val intent = Intent(this@MainActivity, actualizarPapeleria::class.java)
                intent.putExtra("idPapeleria", arreglo[idPapeleriaSeleccionado].id_papeleria)
                intent.putExtra("nombrePapeleria", arreglo[idPapeleriaSeleccionado].nombre_papeleria)
                intent.putExtra("direccion", arreglo[idPapeleriaSeleccionado].direccion)
                intent.putExtra("telefono", arreglo[idPapeleriaSeleccionado].telefono)
                intent.putExtra("fundacion", arreglo[idPapeleriaSeleccionado].fecha_fundacion)
                startActivity(intent)
                return true
            }
            R.id.mp_eliminar -> {
                abrirDialogo()
                return true
            }
            R.id.mp_Ver -> {
                val intent = Intent(this@MainActivity, verProducto::class.java)
                intent.putExtra("idPapeleria", arreglo[idPapeleriaSeleccionado].id_papeleria)
                startActivity(intent)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Esta seguro de eliminar esta papeleria?")
        builder.setPositiveButton(
            "ACEPTAR",
            DialogInterface.OnClickListener { dialog, which ->
                val papeleria= BDPapeleriaHelper(this)
                val arreglo = BDPapeleriaHelper(this).verPapelerias()
                var idEliminar=arreglo[idPapeleriaSeleccionado].id_papeleria
                val resultado = papeleria.eliminarPapeleria(idEliminar)

                if (resultado > 0) {
                    Toast.makeText(this, "DATOS ELIMINADOs", Toast.LENGTH_LONG).show()
                    irActividad(MainActivity::class.java)
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