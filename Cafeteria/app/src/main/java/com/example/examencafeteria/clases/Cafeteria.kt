package com.example.examencafeteria.clases

class Cafeteria(
    public var name:String?,
    public var tiposCafe:Long?,
    public var date: String?,
    public var accept: String?,
    public var hours: Long?
) {
    override fun toString():String{
        return "${name} - ${accept} acepta tarjeta - ${tiposCafe} tipos de cafe - Horas abierto: ${hours} - Fecha de inauguración: ${date}"
    }
}