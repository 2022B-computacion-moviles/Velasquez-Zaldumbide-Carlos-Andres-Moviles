package com.example.deber1cavz

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class BDPapeleriaHelper(context: Context?) : SQLiteOpenHelper(
    context,
    "DBPapeleria",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val tablaPapeleria =
            """
                CREATE TABLE PAPELERIA(
                id_papeleria INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre_papeleria VARCHAR(50),
                direccion VARCHAR(100),
                telefono INTEGER,
                fecha_fundacion DATE
                )
            """.trimIndent()
        db?.execSQL(tablaPapeleria)
        val tablaProducto =
            """
                CREATE TABLE PRODUCTO(
                id_producto INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre_producto VARCHAR(50),
                stock INTEGER,
                precio FLOAT,
                disponible BOOLEAN,
                id_Papeleria INTEGER
                )
            """.trimIndent()
        db?.execSQL(tablaProducto)

        val ingresarPapeleria = """
            INSERT INTO PAPELERIA (nombre_papeleria, direccion, telefono, fecha_fundacion)
            VALUES ("Dilipa","Av. 10 de Agosto",022418753,"12/07/1983");
        """.trimIndent()
        db?.execSQL(ingresarPapeleria)

        val ingresarProducto = """
            INSERT INTO PRODUCTO (nombre_producto, stock, precio, disponible, id_Papeleria)
            VALUES ("PAPEL PERIODICO 65X90 CM DOBLADO",15,0.07,"true",1);
        """.trimIndent()
        db?.execSQL(ingresarProducto)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    /***********************FUNCIONES CRUD PAPELERIA***************************/
    //INSERTAR PAPELERIA
    fun insertarPapeleria(
        nombre_papeleria: String,
        direccion: String,
        telefono: Int?,
        fecha_fundacion: String,
    ): Long {
        val baseDatosLectura = readableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_papeleria", nombre_papeleria)
        values.put("direccion", direccion)
        values.put("telefono", telefono)
        values.put("fecha_fundacion", fecha_fundacion)

        return baseDatosLectura.insert("PAPELERIA", null, values)
    }

    //VER PAPELERIA
    fun verPapelerias():ArrayList<BDPapeleria>{
        val baseDatosLectura = readableDatabase

        var papeleria: BDPapeleria
        val salida=ArrayList<BDPapeleria>()
        var cursor: Cursor? = null
        cursor = baseDatosLectura.rawQuery("SELECT * FROM PAPELERIA", null)
        if(cursor.moveToFirst()){
            do{
                papeleria= BDPapeleria(cursor.getString(0).toInt(),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3).toInt(),
                    cursor.getString(4))
                salida.add(papeleria)
            }while (cursor.moveToNext())
        }
        cursor.close()
        return salida
    }

    //ACTUALIZAR PAPAELERIA
    fun actualizarPapeleria(
        id:Int,
        nombre_papeleria: String,
        direccion: String,
        telefono: Int?,
        fecha_fundacion: String
    ): Int {
        val baseDatosLectura = readableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_papeleria", nombre_papeleria)
        values.put("direccion", direccion)
        values.put("telefono", telefono)
        values.put("fecha_fundacion", fecha_fundacion)
        return baseDatosLectura.update("PAPELERIA", values, "id_papeleria="+id, null)
    }
    //ELIMINAR PAPELERIA
    fun eliminarPapeleria(id: Int?): Int{
        val baseDatosLectura = readableDatabase
        return baseDatosLectura.delete("PAPELERIA", "id_papeleria="+id, null)
    }

    /***********************FUNCIONES CRUD PRODUCTO***************************/
    //INSERTAR PRODUCTO
    fun insertarProducto(
        idPapeleriaPro: Int,
        nombre_papeleria: String,
        direccion: String,
        telefono: Int?,
        fecha_fundacion: String,
    ): Long {
        val baseDatosLectura = readableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_papeleria", nombre_papeleria)
        values.put("direccion", direccion)
        values.put("telefono", telefono)
        values.put("fecha_fundacion", fecha_fundacion)
        values.put("id_Papeleria", idPapeleriaPro)
        return baseDatosLectura.insert("PRODUCTO", null, values)
    }
    //VER PRODUCTO
    fun verProductos(id: Int):ArrayList<BDProducto>{
        val baseDatosLectura = readableDatabase
        var producto: BDProducto
        val salida=ArrayList<BDProducto>()
        var cursor: Cursor? = null
        cursor = baseDatosLectura.rawQuery("SELECT * FROM PRODUCTO WHERE id_Papeleria = ${id} ", null)
        if(cursor.moveToFirst()){
            do{
                producto= BDProducto(cursor.getString(0).toInt(),
                    cursor.getString(1),
                    cursor.getString(2).toInt(),
                    cursor.getString(3).toFloat(),
                    cursor.getString(4).toBoolean(),
                    cursor.getString(5).toInt())
                salida.add(producto)
            }while (cursor.moveToNext())
        }
        cursor.close()
        return salida
    }
}