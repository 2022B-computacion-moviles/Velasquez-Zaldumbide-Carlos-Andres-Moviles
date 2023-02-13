package com.example.movcompcavz

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperPapeleria (
    contexto : Context?,
): SQLiteOpenHelper(
    contexto,
    "base_papeleria", // Nombre de nuestra BDD Sqlite (moviles.sqlite)
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scripSQLCrearTablaPapeleria =
            """
                CREATE TABLE PAPELERIA(
                id_papeleria INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre_papeleria VARCHAR(50),
                direccion VARCHAR(100),
                telefono INTEGER,
                fecha_fundacion DATE
                )
            """.trimIndent()
        db?.execSQL(scripSQLCrearTablaPapeleria)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun verPapelerias():ArrayList<BPapeleria>{
        val baseDatosLectura = readableDatabase
        var papeleria: BPapeleria
        val salida=ArrayList<BPapeleria>()
        var cursor: Cursor? = null
        cursor = baseDatosLectura.rawQuery("SELECT * FROM PAPELERIA", null)
        if(cursor.moveToFirst()){
            do{
                papeleria= BPapeleria(null,"","",null,"")
                papeleria.setidPapeleria(cursor.getString(0).toInt())
                papeleria.setnombrePapeleria(cursor.getString(1))
                papeleria.setdireccion(cursor.getString(2))
                papeleria.settelefono(cursor.getString(3).toInt())
                papeleria.setfecha(cursor.getString(4))
                salida.add(papeleria)
            }while (cursor.moveToNext())
        }
        cursor.close()
        return salida
    }

    fun crearPapeleria(
        nombre_papeleria: String,
        direccion: String,
        telefono: Int,
        fecha: String
    ): Boolean {
        // this.readableDatabse Lectura
        // this.writableDatabse Escritura
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre_papeleria)
        valoresAGuardar.put("direccion", direccion)
        valoresAGuardar.put("telefono", telefono)
        valoresAGuardar.put("fecha", fecha)

        val resultadoGuardar = basedatosEscritura
            .insert(
                "PAPELERIA", // Tabla
                null,
                valoresAGuardar // Valores
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }
}