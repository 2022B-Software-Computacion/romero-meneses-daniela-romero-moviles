package com.example.examencafeteria.clases

class TiposCafe(
    public var nameCafe:String?,
    public var numProduct:Long?,
    public var dateC: String?,
    public var hasMilk: String?,
    public var price: Long?
) {
    override fun toString():String{
        return "${nameCafe} - Precio:${price} - Fecha de introduccion:${dateC} - Num. Productos: ${numProduct}-Es hecho con leche:${hasMilk}"
    }
}