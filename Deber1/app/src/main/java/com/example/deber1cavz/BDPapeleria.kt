package com.example.deber1cavz

import android.content.Context
import android.os.Parcel
import android.os.Parcelable

class BDPapeleria(
    var id_papeleria: Int?,
    var nombre_papeleria: String,
    var direccion: String,
    var telefono: Int?,
    var fecha_fundacion: String,
    //val contexto: Context?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()!!,
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id_papeleria)
        parcel.writeString(nombre_papeleria)
        parcel.writeString(direccion)
        parcel.writeValue(telefono)
        parcel.writeString(fecha_fundacion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BDPapeleria> {
        override fun createFromParcel(parcel: Parcel): BDPapeleria {
            return BDPapeleria(parcel)
        }

        override fun newArray(size: Int): Array<BDPapeleria?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString() : String{
        return "${nombre_papeleria}"
    }
}