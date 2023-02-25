package com.example.deber2cavz

import android.os.Parcel
import android.os.Parcelable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class streamers(
    val nombre: String,
    val tituloStream: String,
    val categoria: String,
    val imagenStream: Int,
    val imagenStreamer: String
): Parcelable  {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(tituloStream)
        parcel.writeString(categoria)
        parcel.writeInt(imagenStream)
        parcel.writeString(imagenStreamer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<streamers> {
        override fun createFromParcel(parcel: Parcel): streamers {
            return streamers(parcel)
        }

        override fun newArray(size: Int): Array<streamers?> {
            return arrayOfNulls(size)
        }
    }
}