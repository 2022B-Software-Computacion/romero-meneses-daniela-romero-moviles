import java.util.*

fun main() {
    println("Hola mundo")
    // Tipos de Variables

    // INMUTABLES (No se pueden reasignar)
    val inmutable: String = "Adrian";
    // inmutable = "Vicente"; //No se puede reasignar

    // MUTABLES ( Re Asignar)
    var mutable: String = "Vicente";
    mutable = "Adrian";

    // val > var
    // ; es opcional

    //Sintaxis Duck typing
    val ejemploVariable = "Ejemplo"
    val edadEjemplo: Int = 12;
    ejemploVariable.trim()

    // Variables primitivas
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'S'
    val mayorEdad: Boolean = true

    // Clases JAVA
    val fechaNacimiento: Date = Date()

    // No tiene truthy ni falsy
    if(true){
    }else{
    }

    // switch no existe
    val estadoCivilWhen = "S"
    when(estadoCivilWhen) {
        ("S") -> {
            println("acercarse")
        }
        "C" -> {
            println("alejarse")
        }
        "UN" -> println("hablar")
        else -> println("No reconocido")
    }
    val coqueteo = if (estadoCivilWhen == "S") "SI" else "NO"

}
// void imprimirNombre(String nombre)
// Unit == void
fun imprimirNombre(nombre: String): Unit {
    println("Nombre : ${nombre}")
}

fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00, //Opcional
    bonoEspecial: Double? = null, //Opcional (Null) -> nullable
): Double {
    //String -> String?
    // Int -> Int?
    // Date -> Date?
    if (bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) + bonoEspecial
    }
}

abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int
    ){//Bloque código constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializado")
    }
}

abstract class Numeros( //Constructor Primario
    //uno: Int, //parámetro
    protected val numeroUno: Int, //propiedad de la clase protected
    protected val numeroDos: Int //propiedad de la clase protected
){
    init { //Bloque de código constructor PRIMARIO
        this.numeroUno
        numeroUno
        this.numeroDos
        numeroDos
        println("inicializado")
    }

}

class Suma(//constructor primario suma
    uno: Int, //parámetro
    dos: Int //parámetro
): Numeros(uno, dos){
    init{ //bloque constructor primario
        this.numeroUno
        this.numeroDos
    }
    constructor(//segundo constructor
        uno: Int?, //parametros
        dos: Int //parametro
    ):this( //llamada al constructor primario
        if(uno ==null) 0 else uno,
        dos
    ){} //Bloque de código del constructor
    constructor(//tercer constructor
        uno: Int, //parametros
        dos: Int? //parametro
    ):this( //llamada al constructor primario
        uno,
        if(dos ==null) 0 else uno
    ){}
    constructor(//cuarto constructor
        uno: Int?, //parametros
        dos: Int? //parametro
    ):this( //llamada al constructor primario
        if(uno ==null) 0 else uno,
        if(dos ==null) 0 else uno
    )
    public fun sumar():Int{
        return numeroUno+numeroDos
    }

    companion object{// atributos y métodos "compartidos" entre las instancias
    val pi=3.14
        fun elevarAlCuadrado(num:Int):Int{
            return num*num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }

    }
}

