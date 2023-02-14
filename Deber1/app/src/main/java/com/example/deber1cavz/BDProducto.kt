package com.example.deber1cavz

import android.os.Parcel
import android.os.Parcelable

class BDProducto(
    var id_producto: Int,
    var nombre_producto: String,
    var stock: Int,
    var precio: Float,
    var disponible: Boolean,
    var idPapeleriaPro: Int,
    //val contexto: Context?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id_producto)
        parcel.writeString(nombre_producto)
        parcel.writeInt(stock)
        parcel.writeFloat(precio)
        parcel.writeByte(if (disponible) 1 else 0)
        parcel.writeInt(idPapeleriaPro)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BDProducto> {
        override fun createFromParcel(parcel: Parcel): BDProducto {
            return BDProducto(parcel)
        }

        override fun newArray(size: Int): Array<BDProducto?> {
            return arrayOfNulls(size)
        }
    }
    override fun toString() : String{
        val salida="id: ${id_producto}\n"+
                "nombre: ${nombre_producto}\n"+
                "pecio: ${precio}\n"+
                "disponible: ${disponible}\n"
        return salida
    }
}