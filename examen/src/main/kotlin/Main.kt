import java.io.File
import java.io.InputStream
import java.time.LocalDate

fun main() {
    do {
        println("Gestion Cafeterias y Tipos de Cafes")
        println("Menú Principal")
        println("1. Añadir nueva Cafeteria")
        println("2. Mostrar Cafeterias con sus tipos de Cafes")
        println("3. Actualizar Cafeteria")
        println("4. Gestión de los tipos de Cafes")
        println("5. Eliminar Cafeteria")
        println("6. Salir")

        val opcion = readln().toInt()
        var listaCafeterias = leerArchivo()


        when (opcion) {
            1 -> {//Crear
                println("Escriba el nombre de la nueva Cafeteria: ")
                val nombreCafeteria: String = readln()
                println("Escriba la cantidad de tipos de cafe que tiene: ")
                val numeroTiposCafe: Int = readln().toInt()
                println("Escriba el numero de empleados de la Cafeteria: ")
                val numeroEmpleados: Int = readln().toInt()
                println("Escriba el nombre del dueño: ")
                val nombreDuenio: String = readln()
                var nuevoCafe: Boolean
                val nuevaListaTipoDeCafe= ArrayList<TipoDeCafe>()
                do {
                    print("Ingrese los datos de los tipos de cafe: \n")

                    println("Nombre del cafe: ")
                    val nombreCafe: String = readln()

                    println("Precio: ")
                    val nuevoPrecio: Double = readln().toDouble()

                    println("Lleva leche, escriba si o no ")
                    val stringLeche: String = readln()
                    val leche: Boolean
                    leche = stringLeche == "si"

                    println("Ingrese la fecha de incorporacion al menu: (aaaa-mm-dd)")
                    val fecha: LocalDate = LocalDate.parse(readln())

                    println("Ingrese los el tamanio del cafe:")
                    val nuevoTamanio = readln()
                    val nuevosTiposDeCafe = TipoDeCafe(nombreCafe,nuevoPrecio,leche,fecha,nuevoTamanio)
                    nuevaListaTipoDeCafe.add(nuevosTiposDeCafe)

                    println("Desea anadir otro tipo de cafe, responda si o no")
                    val seguirAnadiendoCafes = readln()
                    nuevoCafe = seguirAnadiendoCafes == "si"
                }while (nuevoCafe)


                val nuevaCafeteria = Cafeteria(nombreCafeteria,numeroTiposCafe,numeroEmpleados, nombreDuenio,nuevaListaTipoDeCafe)
                listaCafeterias.add(nuevaCafeteria)
                escribirArchivo(listaCafeterias)
            }
            2->{//Mostrar
                listaCafeterias.forEach{
                        cafeteria: Cafeteria ->
                    println("Cafeteria:")
                    println("Nombre: " + cafeteria.nombreCafeteria +
                            "   Cantidad tipos de cafe: " + cafeteria.cantidadTiposCafe +
                            "   Numero de empleados: "+cafeteria.numeroEmpleados +
                            "   Dueño: "+ cafeteria.duenio+"\n")
                    println("Tipos de Cafe que ofrecen: \n")
                    cafeteria.listaTipoDeCafes.forEach{
                            tipoDeCafe: TipoDeCafe ->
                        println("Nombre: " + tipoDeCafe.nombre+
                                "   Precio: "+tipoDeCafe.precio+
                                "   Lleva leche el cafe: "+tipoDeCafe.llevaLeche+
                                "   Fecha que fue incorporado: "+tipoDeCafe.fechaIncorporacion+
                                "   Tamaño:"+tipoDeCafe.tamanio)
                    }
                }
            }
            3->{//Actualizar
                println("Nombre de la cafeteria: ")
                val nombreCafe = readln()
                val auxCafe = Cafeteria()
                listaCafeterias = auxCafe.actualizarCafeteria(listaCafeterias, nombreCafe)
                escribirArchivo(listaCafeterias)
                println("Información actualizada\n")
            }

            4->{//CRUD del producto
                println("Nombre de la cafeteria: ")
                val nombreCafeteria = readln()
                val auxCafeteria = Cafeteria()
                val indiceCafeteria = auxCafeteria.buscarCafeteria(listaCafeterias, nombreCafeteria)

                do{
                    println("Tipos de Cafe: ")
                    println("7. Crear")
                    println("8. Mostrar tipos de cafe")
                    println("9. Actualizar")
                    println("10. Borrar")
                    println("11. Salir")
                    val opcionP = readln().toInt()

                    listaCafeterias = leerArchivo()

                    when(opcionP){
                        7->{ //crear
                            print("Ingrese los datos de los tipos de cafe:\n")

                            println("Nombre del cafe: ")
                            val nombreCafe: String = readln()

                            println("Precio: ")
                            val nuevoPrecio: Double = readln().toDouble()

                            println("Lleva leche, escriba si o no ")
                            val stringLeche: String = readln()
                            val leche: Boolean
                            leche = stringLeche == "si"

                            println("Ingrese la fecha de incorporacion al menu: (aaaa-mm-dd)")
                            val fecha: LocalDate = LocalDate.parse(readln())

                            println("Ingrese los el tamanio del cafe:")
                            val nuevoTamanio = readln()

                            val tipoDeCafe = TipoDeCafe(nombreCafe, nuevoPrecio, leche, fecha, nuevoTamanio)

                            listaCafeterias[indiceCafeteria].listaTipoDeCafes.add(tipoDeCafe)
                            escribirArchivo(listaCafeterias)
                        }
                        8->{ // Mostar
                            println("Tipos de Cafe")
                            val tiposDeCafe = listaCafeterias[indiceCafeteria].listaTipoDeCafes
                            tiposDeCafe.forEach{
                                    cafes: TipoDeCafe ->
                                println("Nombre:" + cafes.nombre+
                                        "   Precio:"+cafes.precio+
                                        "   Lleva leche:"+cafes.llevaLeche+
                                        "   Fecha de incorporacion en el menu: "+cafes.fechaIncorporacion+
                                        "   Tamaño: "+cafes.tamanio)
                            }
                        }
                        9->{// Actualizar
                            println("Nombre del cafe: ")
                            val nombreCafe = readln()
                            val auxCafe = TipoDeCafe()
                            listaCafeterias[indiceCafeteria].listaTipoDeCafes = auxCafe.actualizarCafe(listaCafeterias[indiceCafeteria].listaTipoDeCafes, nombreCafe)
                            escribirArchivo(listaCafeterias)
                            println("Información actualizada\n")
                        }
                        10->{//Borrar
                            println("Nombre del cafe: ")
                            val nombreProd = readln()
                            val auxProd = TipoDeCafe()
                            listaCafeterias[indiceCafeteria].listaTipoDeCafes = auxProd.borrarCafe(listaCafeterias[indiceCafeteria].listaTipoDeCafes, nombreProd)
                            escribirArchivo(listaCafeterias)
                            println("Producto eliminado\n")
                        }
                    }

                }while (opcionP!=11)
            }
            5->{
                println("Nombre de la cafeteria: ")
                val nombreCafeteria = readln()
                var auxCafeteria = Cafeteria()
                listaCafeterias = auxCafeteria.borrarCafeteria(listaCafeterias, nombreCafeteria)
                escribirArchivo(listaCafeterias)
                println("Cafeteria eliminado \n")
            }

        }
    } while (opcion != 6)
}

fun escribirArchivo(texto:ArrayList<Cafeteria>){

    val archivo = File("src/cafeterias.txt")
    archivo.writeText("")
    texto.forEach { cafeteriaEscrita: Cafeteria ->
        archivo.appendText("Cafeteria: ")
        archivo.appendText("Nombre:" + cafeteriaEscrita.nombreCafeteria +
                " :Cantidad tipos de Cafe: " + cafeteriaEscrita.cantidadTiposCafe+
                " :Numero de Empleados: "+cafeteriaEscrita.numeroEmpleados+
                " :Dueño: "+cafeteriaEscrita.duenio+"\n")

        cafeteriaEscrita.listaTipoDeCafes.forEach { cafe: TipoDeCafe ->
            archivo.appendText("Cafes: ")
            archivo.appendText("Nombre: " + cafe.nombre+
                    " :Precio: "+cafe.precio+
                    " :Lleva leche:"+cafe.llevaLeche.toString()+
                    " :Fecha de Incorporacion al menu:"+cafe.fechaIncorporacion+
                    " :Tamaño:"+cafe.tamanio+"\n\n")
        }
    }
}

fun leerArchivo():ArrayList<Cafeteria>{
    val inputStream: InputStream = File("src/cafeterias.txt").inputStream()
    val lineas = mutableListOf<String>()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineas.add(it) } }

    var listaCafeterias = ArrayList<Cafeteria>()
    var indiceSuper = 0

    lineas.forEach{ linea: String->
        val tokens = listOf(*linea.split("\\s*:\\s*".toRegex()).toTypedArray())

        if(tokens[0].equals("Cafeteria")){
            val cafeteria = Cafeteria()
            cafeteria.nombreCafeteria = tokens[2]
            cafeteria.cantidadTiposCafe = tokens[4].toInt()
            cafeteria.numeroEmpleados = tokens[6].toInt()
            cafeteria.duenio = tokens[8]
            listaCafeterias.add(cafeteria)
            indiceSuper = listaCafeterias.indexOf(cafeteria)
        }else{
            if(tokens[0].equals("Cafes") ){
                val cafes = TipoDeCafe()
                cafes.nombre = tokens[2]
                cafes.precio = tokens[4].toDouble()
                cafes.llevaLeche = tokens[6].toBoolean()
                cafes.fechaIncorporacion = LocalDate.parse(tokens[8])
                cafes.tamanio = tokens[10]
                listaCafeterias[indiceSuper].listaTipoDeCafes.add(cafes)
            }
        }
    }
    return listaCafeterias

}
