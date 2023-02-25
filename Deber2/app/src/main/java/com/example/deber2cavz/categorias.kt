package com.example.deber2cavz

import android.os.Parcel
import android.os.Parcelable

class categorias(
    val categoria: String,
    val espectadores: String,
    val subcategoria: String,
    val imagenCategoria: Int,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<categorias> {
        override fun createFromParcel(parcel: Parcel): categorias {
            return categorias(parcel)
        }

        override fun newArray(size: Int): Array<categorias?> {
            return arrayOfNulls(size)
        }
    }
}