package com.example.cavzexameniib

class BDProducto {
    var id_producto: String= ""
    var nombre_producto: String= ""
    var stock: Int?=null
    var precio: Float?=null
    var disponible: Boolean= false
    //var idPapeleriaPro: Int?= null

    constructor(){}

    override fun toString() : String{
        return "${nombre_producto}"
    }
}