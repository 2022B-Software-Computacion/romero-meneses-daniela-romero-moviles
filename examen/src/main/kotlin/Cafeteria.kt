class Cafeteria {
    var nombreCafeteria: String =""
    var cantidadTiposCafe: Int =0
    var numeroEmpleados: Int=0
    var duenio:String=""
    var listaTipoDeCafes= ArrayList<TipoDeCafe>()

    //Crea la Cafeteria con una lista de Cafes
    constructor(nombreCafeteria: String,cantidadTiposCafe: Int, numeroEmpleados:Int, duenio:String, tiposDeCafe: ArrayList<TipoDeCafe>){
        this.nombreCafeteria = nombreCafeteria
        this.cantidadTiposCafe = cantidadTiposCafe
        this.numeroEmpleados = numeroEmpleados
        this.duenio = duenio
        this.listaTipoDeCafes = tiposDeCafe
    }

    constructor(){

    }

    // Busca la informacion de una Cafeteria
    fun buscarCafeteria(listaCafeterias:ArrayList<Cafeteria>, nombreCafeteria:String):Int{
        var indice = 0
        listaCafeterias.forEach{
                cafeteriaAux:Cafeteria ->
                if(cafeteriaAux.nombreCafeteria.equals(nombreCafeteria)){
                    indice =  listaCafeterias.indexOf(cafeteriaAux)
                }
        }
        return indice
    }

    // Actualiza elementos de la Cafeteria
    fun actualizarCafeteria(listaCafeterias:ArrayList<Cafeteria>, nuevoNombre:String):ArrayList<Cafeteria>{
        var auxCafe = Cafeteria()
        val indiceCafeteria = auxCafe.buscarCafeteria(listaCafeterias, nuevoNombre)
        auxCafe = listaCafeterias[indiceCafeteria]
        val info: MutableMap<String, String> = mutableMapOf(
            "Nombre" to auxCafe.nombreCafeteria,
            "Cantidad de Tipos de Cafes" to auxCafe.cantidadTiposCafe.toString(),
            "Cantidad de Empleados" to auxCafe.numeroEmpleados.toString(),
            "Dueño" to auxCafe.duenio)

        println("¿Qué información quieres cambiar?: ")
        var auxCont = 0
        info.forEach{
                (k, v) ->
            auxCont = auxCont+1
            println("   $auxCont. $k : $v")
        }
        val eleccion = readln().toInt()

        println("Escribe el nuevo valor: ")
        val nuevaInformacion = readln()
        when(eleccion){
            2->{listaCafeterias[indiceCafeteria].cantidadTiposCafe=nuevaInformacion.toInt()}
            3->{listaCafeterias[indiceCafeteria].numeroEmpleados= nuevaInformacion.toInt()}
            4->{listaCafeterias[indiceCafeteria].duenio=nuevaInformacion}
        }
        return listaCafeterias
    }
    fun borrarCafeteria(listaCafeterias:ArrayList<Cafeteria>, nombreCafeteria:String):ArrayList<Cafeteria>{
        var auxCafeteria = Cafeteria()
        val indiceCafeteria = auxCafeteria.buscarCafeteria(listaCafeterias, nombreCafeteria)
        listaCafeterias.removeAt(indiceCafeteria)

        return listaCafeterias
    }

}