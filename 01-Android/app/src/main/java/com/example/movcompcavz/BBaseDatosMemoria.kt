package com.example.movcompcavz

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador= arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1,"Carlos","a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2,"Andres","b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3,"Carolina","c@c.com")
                )
        }
    }
}