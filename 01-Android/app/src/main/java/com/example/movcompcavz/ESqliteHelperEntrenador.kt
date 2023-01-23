package com.example.movcompcavz

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(
    contexto:Context?
):SQLiteOpenHelper(
    contexto,
    "moviles",//nombre de nuestra BDD Sqlite(moviles.sqlite)
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador="""
            CREATE TABLE ENTRENADOR(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre VARCHAR(50),
            descripcion VARCHAR(50)
            )
        """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    fun crearEntrenador(
        nombre:String,
        descripcion: String
    ):Boolean{
        val basedatosEscritura=writableDatabase
        val valoresAGuardar=ContentValues()
        valoresAGuardar.put("nombre",nombre)
        valoresAGuardar.put("descripcion",descripcion)
        val resultadoGuardar=basedatosEscritura
            .insert(
                "ENTRENADOR",//tabla
            null,
                valoresAGuardar//valores
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt()==-1) false else true
    }
    fun eliminarEntrenadorFormulario(id:Int):Boolean{
        val conexionEscritura=writableDatabase
        val resultadoEliminacion=conexionEscritura
            .delete(
                "ENTRENADOR",
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt()==-1) false else true
    }

    fun actualizarEntrenadorFormulario(
        nombre:String,
        descripcion:String,
        idActualizar: Int
    ):Boolean{
        val conexionEscritura=writableDatabase
        val valoresActualizar=ContentValues()
        valoresActualizar.put("nombre",nombre)
        valoresActualizar.put("descripcion",descripcion)
        val resultadoActualizacion=conexionEscritura
            .update(
                "ENTRENADOR",//Nombre tabla
                valoresActualizar,
                "id=?",
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualizacion==-1) false else true
    }
    fun consultarEntrenadorPorId(id:Int):BEntrenador{
        //val baseDatosLectura=this.readableDatabase
        val baseDatosLectura=readableDatabase
        val scriptConsultarUsuario="SELECT * FROM ENTRENADOR WHERE ID=?"
        val resultadoConsultarLectura=baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            arrayOf(
                id.toString()
            )
        )
        val existeUsuario=resultadoConsultarLectura.moveToFirst()
        val usuarioEncontrado=BEntrenador(0,"","")
        //Logica obtener el usuario
        do{
            val id=resultadoConsultarLectura.getInt(0)
            val nombre=resultadoConsultarLectura.getString(1)
            val descripcion=
                resultadoConsultarLectura.getString(2)
            if(id!=null){
                usuarioEncontrado.id=id
                usuarioEncontrado.nombre=nombre
                usuarioEncontrado.descripcion=descripcion
            }
        }while(resultadoConsultarLectura.moveToNext())
        resultadoConsultarLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }
}