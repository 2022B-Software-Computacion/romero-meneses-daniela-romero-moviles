// kotlin/TipoDeCafe.kt
import java.time.LocalDate

class TipoDeCafe {
    var nombre: String =""
    var precio: Double =0.0
    var llevaLeche: Boolean = true
    var fechaIncorporacion: LocalDate = LocalDate.parse("2000-01-01")
    var tamanio: String = ""
    //CREATE
    constructor(nombre:String,precio:Double,llevaLeche:Boolean, fechaPedido:LocalDate, tamanio:String){
        this.nombre = nombre
        this.precio = precio
        this.llevaLeche = llevaLeche
        this.fechaIncorporacion = fechaPedido
        this.tamanio = tamanio
    }

    constructor(){}

    fun buscarCafe(listaCafeteria:ArrayList<TipoDeCafe>, nombreCafe:String):Int{
        var indice = 0
        listaCafeteria.forEach{
                cafe:TipoDeCafe ->
            if(cafe.nombre.equals(nombreCafe)){
                indice =  listaCafeteria.indexOf(cafe)
            }
        }
        return indice
    }

    fun actualizarCafe(listaTipoCafe:ArrayList<TipoDeCafe>, nombreTipoCafe:String):ArrayList<TipoDeCafe>{
        var auxCafe = TipoDeCafe()
        val indiceCafe = auxCafe.buscarCafe(listaTipoCafe, nombreTipoCafe)
        auxCafe = listaTipoCafe[indiceCafe]
        val info: MutableMap<String, String> = mutableMapOf("Nombre" to auxCafe.nombre,
            "Precio" to auxCafe.precio.toString(),
            "Lleva leche" to auxCafe.llevaLeche.toString(),
            "Fecha de Incorporacion en el menu" to auxCafe.fechaIncorporacion.toString(),
            "Tamaño" to auxCafe.tamanio.toString())

        println("¿Qué información quieres cambiar?: ")
        var auxCont = 0
        info.forEach{
                (k, v) ->
            auxCont = auxCont+1
            println("   $auxCont. $k : $v")
        }
        val eleccion = readln().toInt()

        println("Escribe el nuevo valor: ")
        val newInfo = readln()
        when(eleccion){
            2->{listaTipoCafe[indiceCafe].precio=newInfo.toDouble()}
            3->{listaTipoCafe[indiceCafe].llevaLeche=newInfo.toBoolean()}
            4->{listaTipoCafe[indiceCafe].fechaIncorporacion=LocalDate.parse(newInfo)}
            5->{listaTipoCafe[indiceCafe].tamanio=newInfo.toString()}
        }
        return listaTipoCafe
    }
    fun borrarCafe(listaCafes:ArrayList<TipoDeCafe>, nombreCafe:String):ArrayList<TipoDeCafe>{
        val auxCafe = TipoDeCafe()
        val indiceCafe = auxCafe.buscarCafe(listaCafes, nombreCafe)
        listaCafes.removeAt(indiceCafe)

        return listaCafes
    }

}