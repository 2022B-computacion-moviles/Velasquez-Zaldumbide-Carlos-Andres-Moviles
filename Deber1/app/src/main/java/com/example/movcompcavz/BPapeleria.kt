package com.example.movcompcavz

import android.os.Parcel
import android.os.Parcelable

class BPapeleria(
    private var id_papeleria: Int?,
    private var nombre_papeleria: String,
    private var direccion: String,
    private var telefono: Int?,
    private var fecha: String
){
    init{
        id_papeleria
        nombre_papeleria
        direccion
        telefono
        fecha
    }
    fun setidPapeleria(idpapeleria: Int?){
        this.id_papeleria=idpapeleria
    }
    fun setnombrePapeleria(nombrepapeleria: String){
        this.nombre_papeleria=nombrepapeleria
    }
    fun setdireccion(direccion: String){
        this.direccion=direccion
    }
    fun settelefono(telefono: Int?){
        this.telefono=telefono
    }
    fun setfecha(fecha: String){
        this.fecha=fecha
    }
}