package com.example.cavzexameniib

class BDPapeleria{
    var id_papeleria: String = ""
    var nombre_papeleria: String = ""
    var direccion: String=""
    var telefono: Int?=null
    var fecha_fundacion: String=""

    constructor(){}
    override fun toString() : String{
        return "${nombre_papeleria}"
    }
}