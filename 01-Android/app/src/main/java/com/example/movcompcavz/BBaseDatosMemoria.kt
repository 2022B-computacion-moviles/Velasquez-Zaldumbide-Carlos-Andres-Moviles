package com.example.movcompcavz

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador= arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador("Carlos","a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Andres","b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Carolina","c@c.com")
                )
        }
    }
}