fun main(args: Array<String>) {
    println("Hello World!")
    //Tipos de variables
    //Inmutables (no re asignar)
    val inmutable: String="Carlos";//nos puede servir como constante
    //inmutable="Velasquez"//no su puede hacer esto
    //Mutables (re asignar)
    val mutable: String="Carlos";
    //mutable="Velasquez"

    //val > vars

    //Duck Typing no es necesario el ; y tampoco poner que tipo de variable es
    val ejemploVariable = "Ejemplo"
    ejemploVariable.trim()
    val edadEjemplo: Int = 12

    //Variables primitivas
    val nombreProfesor:String = "Carlos"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'S'
    val mayorEdad=true
    //Clases
    val fechaNacimiento:Date = Date() //no se usa "new" en clases
    if(true){}else{}
    //switch no existe
    val estadoCivilWhen = "S"
    when (estadoCivilWhen){
        ("S")->{
            println("Soltero")
        }
        "C"->println("Casado")
        else->println("Desconocido")
    }
    val coqueto=if(estadoCivilWhen=="S") "Si" else "No"

    /*val sumaUno=Suma(1,2)
    val sumaDos=Suma(1,null)
    val sumaTres=Suma(null,2)
    val sumaCuatro=Suma(null,null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.historialSumas)*/

    //Arreglo estatico
    val arregloEstatico: Array<Int> = arrayOf(1,2,3)
    println(arregloEstatico)
    //Arrgelo dinamico
    val arregloDinamico: ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    //OPERADORES-> sirven para los arreglos estáticos y dinámicos
    //FOR EACH->Unit}
    //Iterar un arreglo

    val respuestaForEach: Unit=arregloDinamico
        .forEach{
            valorActual:Int->
            println("Valor actual:$valorActual")
        }
    arregloEstatico
        .forEachIndexed{indice:Int,valorActual:Int->
            println("Valor actual:${valorActual} Indice:${indice}")
        }
    println(respuestaForEach)

    //Operador map, ayuda a modifica el arreglo a mutar
    val respuestaMap:List<Double> = arregloDinamico
        .map { valorActual: Int->
            return@map valorActual.toDouble()+100.00
        }
    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map { it+15 }
    //    .map { valorActual: Int->
    //        return@map valorActual.toDouble()+15
    //    }
    println(respuestaMapDos)

    //Operador de filtro de arreglo(filter), no modifica el arreglo
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual>5//expresion condicion
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter { it<=5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    //Operador OR AND
    //OR-> ANY (algo cumple)
    //AND-> ALL (todos cumple)
    val respuestaAny:Boolean = arregloDinamico
        .any{valorActual:Int->
            return@any(valorActual>5)
        }
    println(respuestaAny)//true

    val respuestaAll:Boolean = arregloDinamico
        .all{valorActual:Int->
            return@all(valorActual>5)
        }
    println(respuestaAll)//false

    //Operador reduce
    // Valor acumulado = 0 (Simepre 0 en lenguaje kotlin)
    //[1,2,3,4,5]->Sume todos los valores del arreglo
    //valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion1
    //valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion2
    //valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion2
    //valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion2
    //valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion2

    val respuestaReduce: Int = arregloDinamico
        .reduce{
            acumulado: Int,valorActual:Int ->
            return@reduce (acumulado+valorActual)//logica negocio
        }
    println(respuestaReduce)





}

fun imprimirNombre(nombre:String):Unit{//en kotlin Unit es el void
    println("Nombre:${nombre}")
}
fun calcularSueldo(
    sueldo:Double,//requerido
    tasa:Double=12.00,//opcional por defecto
    bonoEspecial:Double?=null//opcional nulo
):Double{
    //String->String?
    //Int->Int
    //Date->Date?
    if(bonoEspecial!=null){//si no pongo unaverificacion de q no es nullo no se compilara dara un error
        return sueldo*tasa*bonoEspecial
    }
    return sueldo*tasa
}

abstract class  NumerosJava{
    protected  val numeroUno:Int
    private  val numeroDos:Int
    constructor(
        uno:Int,//parametro
        dos: Int//parametro
    ){
        this.numeroUno=uno;
        this.numeroDos=dos;
        println("Iniciando")
    }
}

abstract class Numeros(//Constructor primario
    //uno;Int,//Parametro
    //public var uno: Int,//Propiedad de la clase publico
    protected val numeroUno:Int,//propiedad
    protected val numeroDos: Int//propiedad
){
    init{
        //this.numeroUno;//this es opcional
        //this.numeroDos;
        numeroUno
        numeroDos
        println("Iniciando")
    }
}

class  Suma(//Constructor primario suma
    uno:Int,//Parametro
    dos:Int,//Parametro
):Numeros(//heredamos de la clase numeros
    //Super constructor numeros
    uno,
    dos,
){
    init{//bloque constructor primario
        this.numeroUno
        this.numeroDos
    }

    constructor(//segundo constructor
        uno:Int?,//parametros
        dos:Int//parametros
    ):this(
        if(uno==null) 0 else uno,
        dos
    ){}
    constructor(//Tercer constructor
        uno:Int,//parametros
        dos:Int?//parametros
    ):this(
        uno,
        if(dos==null) 0 else dos
    ){}
    constructor(//cuarto constructor
        uno:Int?,//parametros
        dos:Int?//parametros
    ):this(
        if(uno==null) 0 else uno,
        if(dos==null) 0 else dos,
    ){}
    fun sumar():Int{
        val total=numeroUno+numeroDos
        agregarHistorial(total)
        return total
    }

    //todo lo que se ponga aqui va estar disponible en la clase
    companion object{
        val pi=3.14//suma.pi->3.14
        val historialSumas= arrayListOf<Int>()//Suma.historialSumas
        fun agregarHistorial(valorNuvaSuma:Int){//Suma.agregarHistorial
            historialSumas.add(valorNuvaSuma)
        }
    }
}