package com.example.cavzexameniib

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class BDPapeleriaHelper {
    /***********************FUNCIONES CRUD PAPELERIA***************************/
    //INSERTAR PAPELERIA
    fun insertarPapeleria(
        nombre_papeleria: String,
        direccion: String,
        telefono: Int?,
        fecha_fundacion: String
    ):Int {
        var db= FirebaseFirestore.getInstance()
        db.collection("papeleria").document().set(
            hashMapOf("nombre_papeleria" to nombre_papeleria,
                "direccion" to direccion,
                "telefono" to telefono,
                "fecha_fundacion" to fecha_fundacion)
        )
        return 1
    }

    //ACTUALIZAR PAPAELERIA
    fun actualizarPapeleria(
        id:String,
        nombre_papeleria: String,
        direccion: String,
        telefono: Int,
        fecha_fundacion: String
    ): Int {
        val db= FirebaseFirestore.getInstance()
        val docRef = db.collection("papeleria").document(id)
        val values= HashMap<String, Any>()
        values["nombre_papeleria"] = nombre_papeleria
        values["direccion"] = direccion
        values["telefono"] = telefono
        values["fecha_fundacion"] = fecha_fundacion
        // 3. Actualizar los datos en Firestore
        docRef.update(values).addOnSuccessListener {
            Log.d(TAG, "Datos actualizados correctamente")
        }.addOnFailureListener { e ->
            Log.e(TAG, "Error al actualizar los datos", e)
        }
        return 1
    }

    //ELIMINAR PAPELERIA
    fun eliminarPapeleria(id: String): Int{
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("papeleria").document(id)
        docRef.delete().addOnSuccessListener {
            Log.d(TAG, "Documento eliminado correctamente")
        }.addOnFailureListener { e ->
            Log.e(TAG, "Error al eliminar el documento", e)
        }
        return 1
    }

    /***********************FUNCIONES CRUD PRODUCTO***************************/
    //INSERTAR PRODUCTO
    fun insertarProducto(
        idPapeleriaPro: String,
        nombre_producto: String,
        stock: Int?,
        precio: Float,
        disponibilidad: Boolean
    ): Long {
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("papeleria").document(idPapeleriaPro)
        val nuevaSubcoleccion = hashMapOf(
            "nombre_producto" to nombre_producto,
            "stock" to stock,
            "precio" to precio,
            "disponible" to disponibilidad,
        )
        val insertar = docRef.collection("producto")
        insertar.add(nuevaSubcoleccion)
        return 1
    }

    //ACTUALIZAR PRODUCTO
    fun actualizarProducto(
        idProducto: String,
        idPapeleriaPro: String,
        nombre_producto: String,
        stock: Int,
        precio: Float,
        disponibilidad: Boolean
    ): Int {
        val db = FirebaseFirestore.getInstance()
        val productoNuevos = db.collection("papeleria").document(idPapeleriaPro).collection("producto")
        val values=HashMap<String, Any>()
        values["nombre_producto"] = nombre_producto
        values["stock"] = stock
        values["precio"] = precio
        values["disponible"] = disponibilidad
        productoNuevos.document(idProducto).update(values)
        return 1
    }

    //ELIMINAR PRODUCTO
    fun eliminarProducto(idPapeleria: String, idProducto: String): Int{
        val db = Firebase.firestore
        val eliminar = db.collection("papeleria").document(idPapeleria).collection("producto").document(idProducto)
        eliminar.delete().addOnSuccessListener {
            Log.d(TAG, "Documento eliminado correctamente")
        }.addOnFailureListener { e ->
            Log.e(TAG, "Error al eliminar el documento", e)
        }
        return 1
    }
}

