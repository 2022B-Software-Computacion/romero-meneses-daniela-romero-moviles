package com.example.darmapplication

class BBaseDatosEntrenador {
    companion object{
        val arregloEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloEntrenador
                .add(
                    BEntrenador("Adrian", "a@a.com")
                )
            arregloEntrenador
                .add(
                    BEntrenador("Vicente", "b@b.com")
                )
            arregloEntrenador
                .add(
                    BEntrenador("Carolina", "c@c.com")
                )
        }
    }
}