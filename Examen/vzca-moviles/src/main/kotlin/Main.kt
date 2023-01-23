import java.io.File
import java.io.IOException
import java.util.*
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

fun main(args: Array<String>) {
    //VARIABLES
    val fileName= arrayOf("C:/Users/escritorio.virtual20/Desktop/MovilesCarlosV/Velasquez-Zaldumbide-Carlos-Andres-Moviles/Examen/Papeleria.txt",
        "C:/Users/escritorio.virtual20/Desktop/MovilesCarlosV/Velasquez-Zaldumbide-Carlos-Andres-Moviles/Examen/Producto.txt")
    var bandera=true
    var banderaEliminar=true
    var banderaActualizar=true
    var banderaAuxActualizar=true

    //OPERACIONES CRUD
    do{
        when (opcionesOperaciones(bandera)){
            (1)->{
                //INSERTAR
                val opcionAux=opcionEntidades(bandera)
                insertarDatos(fileName[opcionAux-1],opcionAux)
            }
            (2)->{
                //VER
                println("\nLos datos son los siguientes:")
                verDatos(fileName[opcionEntidades(bandera)-1])
            }
            (3)->{
                //ELIMINAR
                val opcionAux=opcionEntidades(bandera)
                println("\nLos datos son los siguientes:")
                verDatos(fileName[opcionAux-1])
                println("---------------------------------------------")
                while(banderaEliminar){
                    print("Ingrese el indice que va a eliminar: ")
                    try{
                        val indice=readln().toInt()
                        if(verificarIndice(fileName[opcionAux-1],indice)){
                            eliminarDato(fileName[opcionAux-1],indice)
                            banderaEliminar=false
                        }
                    }catch (e: NumberFormatException){
                        println("Error solo se aceptan enteros, ingrese de nuevo")
                    }
                }
                banderaEliminar=true
            }
            (4)->{
                //ACTUALIZAR
                while(banderaAuxActualizar){
                    val opcionAux=opcionEntidades(bandera)
                    println("\nLos datos son los siguientes:")
                    verDatos(fileName[opcionAux-1])
                    println("---------------------------------------------")
                    while(banderaActualizar){
                        print("Ingrese el indice que va a actualizar: ")
                        try{
                            val indice=readln().toInt()
                            if(verificarIndice(fileName[opcionAux-1],indice)){
                                actualizarDato(fileName[opcionAux-1],indice,opcionAux)
                                banderaActualizar=false
                            }
                        }catch (e: NumberFormatException){
                            println("Error solo se aceptan enteros, ingrese de nuevo")
                        }
                    }
                    var aux=0
                    do{
                        println("Desea seguir modificando (s/n): ")
                        val conf= readln()
                        when(conf){
                            ("s"),("S")->{
                                println("Nueva modificacion...")
                                aux=1
                            }
                            ("n"),("N")->{
                                print("Actualizacion terminada")
                                banderaAuxActualizar=false
                                aux=1
                            }
                            else-> {
                                println("Esa opcion no existe, responda con un (s/n)")
                            }
                        }
                    }while(aux==0)
                    banderaActualizar=true
                }
                banderaAuxActualizar=true
            }
        }
        print("\nPresione s para continuar: ")
        val continuar=readln()
    }while(continuar=="s" || continuar=="S")
    println("PROGRAMA TERMINADO")
}

//*************************FUNCION ESCOGER LAS OPERACIONES CRUD*************************
fun opcionesOperaciones(bandera:Boolean):Int{
    println("---------------------------")
    println("| 1. Crear                |")
    println("---------------------------")
    println("| 2. Ver                  |")
    println("---------------------------")
    println("| 3. Eliminar             |")
    println("---------------------------")
    println("| 4. Actualizar           |")
    println("---------------------------")
    while(bandera){
        print("Ingrese el numero de la opcion que desea: ")
        try {
            val opcionCRUD=readln().toInt()
            when (opcionCRUD){
                (1),(2),(3),(4)->{
                    return opcionCRUD
                }
                else->{
                    println("Error esa opcion no existe, ingrese de nuevo")
                }
            }
        }catch (e: NumberFormatException){
            println("Error solo se aceptan enteros, ingrese de nuevo")
        }
    }
    return -1
}

//*************************FUNCION ESCOGER LAS ENTIDADES*************************
fun opcionEntidades(bandera:Boolean):Int{
    println("\nLAS ENTIDADES QUE SE TIENES SON:")
    println("---------------------------")
    println("| 1. Papeleria            |")
    println("---------------------------")
    println("| 2. Producto             |")
    println("---------------------------")
    while(bandera){
        print("Ingrese el numero de la entidad que va a usar: ")
        try{
            val opcionEntidad=readln().toInt()
            when (opcionEntidad){
                (1),(2)->{
                    return opcionEntidad
                }
                else->{
                    println("Error esa opcion no existe, ingrese de nuevo")
                }
            }
        }catch (e: NumberFormatException){
            println("Error solo se aceptan enteros, ingrese de nuevo")
        }
    }
    return -1
}

//*************************FUNCIONES PARA INSERTAR DATOS DENTRO DEL TXT*************************
fun insertarDatos(file: String,entidad:Int):Unit{
    val path = Paths.get(file)
    val id:String=(obtenerUltimoDato(file)+1).toString()
    var data= ""
    var disponibilidad=true
    when(entidad){
        (1)->{
            println("Ingrese los datos para Papeleria")
            print("Nombre: ")
            val nombre_papeleria=readln()
            print("Direccion: ")
            val direccion=readln()
            print("Telefono: ")
            val telefono=readln()
            print("Fecha de fundacion formato(dd/mm/aaaa): ")
            val fecha_fundacion=readln()
            data="\n"+id+','+nombre_papeleria+','+direccion+','+telefono+','+fecha_fundacion
        }
        (2)->{
            println("Ingrese los datos para Producto")
            print("Nombre: ")
            val nombre_producto=readln()
            print("Stock: ")
            val stock=readln()
            print("Precio: ")
            val precio=readln()
            if(stock.toInt()==0){
                disponibilidad=false
            }
            data="\n"+id+','+nombre_producto+','+stock+','+precio+','+disponibilidad
        }
    }
    try {
        Files.write(path, data.toByteArray(), StandardOpenOption.APPEND)
        println("Datos agragados exitosamente")
    } catch (e: IOException) {
        e.printStackTrace()
    }
}
fun obtenerUltimoDato(file:String):Int{
    val line= mutableListOf("")
    line.removeAt(0)
    try {
        val sc = Scanner(File(file))
        while (sc.hasNextLine()) {
            var aux=sc.nextLine()
            if(aux!=""){
                line.add(aux)
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    val tam:Int=line.size
    return line.get(tam-1)[0].toString().toInt()
}

//*************************FUNCION PARA VER LOS DATOS DENTRO DEL TXT*************************
fun verDatos(file:String):Unit{
    //LEER ARCHIVOS
    try {
        val sc = Scanner(File(file))
        while (sc.hasNextLine()) {
            var aux=sc.nextLine()
            if(aux!=""){
                println(aux)
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

//*************************FUNCIONES PARA ELIMINAR DATOS DENTRO DEL TXT*************************
fun eliminarDato(file:String,indiceEliminar:Int):Unit{
    val line= mutableListOf("")
    line.removeAt(0)
    try {
        val sc = Scanner(File(file))
        while (sc.hasNextLine()) {
            var aux=sc.nextLine()
            if(aux!=""){
                if(aux[0].toString().toInt()!=indiceEliminar){
                    line.add(aux)
                }
            }
        }
        var aux=0
        do{
            print("Esta seguro de eliminar los datos con indice ${indiceEliminar} (s/n): ")
            val conf= readln()
            when(conf){
                ("s"),("S")->{
                    val archivo = File(file)
                    var salida=""
                    for(j in line){
                        salida+=j+"\n"
                    }
                    archivo.printWriter().use { out ->
                        out.println(salida)
                    }
                    println("\nLos datos despues de eliminar son los siguientes:")
                    verDatos(file)
                    aux=1
                }
                ("n"),("N")->{
                    print("Eliminacion cancelada")
                    aux=1
                }
                else->{
                    println("Esa opcion no existe, responda con un (s/n)")
                }
            }
        }while(aux==0)
    } catch (e: IOException) {
        e.printStackTrace()
    }
}
//*************************FUNCIONES PARA ACTUALIZAR DATOS DENTRO DEL TXT*************************
fun actualizarDato(file:String,indice:Int,opcionAux:Int):Unit{
    val line= mutableListOf("")
    line.removeAt(0)
    //Se obtiene los datos del archivo y se agrega a una lista
    try {
        val sc = Scanner(File(file))
        while (sc.hasNextLine()) {
            var aux=sc.nextLine()
            if(aux!=""){
                line.add(aux)
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    //Se obtiene los datos acorde al indice ingresado
    val str=line.get(indice-1)
    println("Los datos a actualizar son:")
    val delim=","
    val lista= str.split(delim).toMutableList()
    var i=0

    when (opcionAux){
        //CASAO QUE LA ENTIDAD SEA LA PAPELERIA
        (1)->{
            //Se asiganan a una lista los datos modificables
            for(j in lista){
                if(i!=0){
                    print(i.toString()+". "+j+"\n")
                }
                i++
            }
            i=0
            println("--------------------------------")
            //Ingreso del indice del dato que se va a modificar
            var opc=0
            do{
                print("Ingrese el numero del dato que desea cambiar: ")
                try{
                    opc=readln().toInt()
                    if(opc<=0 || opc>=lista.size){
                        println("Error esa opcion no existe, ingrese de nuevo")
                    }
                    else{
                        print("Ingrese el nuevo dato: ")
                        lista[opc]=readln();
                        var nuevo=""
                        var tam=lista.size
                        for(j in lista){
                            if(i<tam-1){
                                nuevo+=j+","
                            }
                            else{
                                nuevo+=j
                            }
                            i++
                        }
                        line.set(indice-1,nuevo)
                        var salida=""
                        for(j in line){
                            salida+=j+"\n"
                        }
                        val archivo = File(file)
                        archivo.printWriter().use { out ->
                            out.println(salida)
                        }
                        println("\nLos datos despues de actualizar son los siguientes:")
                        verDatos(file)
                    }
                }catch (e: NumberFormatException){
                    println("Error solo se aceptan enteros, ingrese de nuevo")
                }
            }while(opc<=0 || opc>=lista.size)
        }
        //CASAO QUE LA ENTIDAD SEA EL PRODUCTO
        (2)->{
            //Se asiganan a una lista los datos modificables
            for(j in lista){
                if(i!=0 && i!=lista.size-1){
                    print(i.toString()+". "+j+"\n")
                }
                i++
            }
            i=0
            println("--------------------------------")
            //Ingreso del indice del dato que se va a modificar
            var opc=0
            do{
                print("Ingrese el numero del dato que desea cambiar: ")
                try{
                    opc=readln().toInt()
                    if(opc<=0 || opc>=lista.size){
                        println("Error esa opcion no existe, ingrese de nuevo")
                    }
                    else{
                        if(opc==2){
                            print("Ingrese el nuevo dato: ")
                            lista[opc]=readln();
                            if(lista[opc].toInt()<0){
                                println("No se puede poner stock negativo")
                            }else{
                                var nuevo=""
                                var tam=lista.size
                                if(lista[opc].toInt()==0){
                                    lista[4]=false.toString()
                                }
                                else{
                                    lista[4]=true.toString()
                                }
                                for(j in lista){
                                    if(i<tam-1){
                                        nuevo+=j+","
                                    }
                                    else{
                                        nuevo+=j
                                    }
                                    i++
                                }
                                line.set(indice-1,nuevo)
                                var salida=""
                                for(j in line){
                                    salida+=j+"\n"
                                }
                                val archivo = File(file)
                                archivo.printWriter().use { out ->
                                    out.println(salida)
                                }
                                println("\nLos datos despues de actualizar son los siguientes:")
                                verDatos(file)
                            }
                        }
                        else{
                            print("Ingrese el nuevo dato: ")
                            lista[opc]=readln();
                            var nuevo=""
                            var tam=lista.size
                            for(j in lista){
                                if(i<tam-1){
                                    nuevo+=j+","
                                }
                                else{
                                    nuevo+=j
                                }
                                i++
                            }
                            line.set(indice-1,nuevo)
                            var salida=""
                            for(j in line){
                                salida+=j+"\n"
                            }
                            val archivo = File(file)
                            archivo.printWriter().use { out ->
                                out.println(salida)
                            }
                            println("\nLos datos despues de actualizar son los siguientes:")
                            verDatos(file)
                        }
                    }
                }catch (e: NumberFormatException){
                    println("Error solo se aceptan enteros, ingrese de nuevo")
                }
            }while(opc<=0 || opc>=lista.size)
        }
    }



}

//******************FUNCIONES PARA VERIFICAR QUE EXISTA EL INDICE DENTRO DEL TXT******************
fun verificarIndice(file:String,indiceEliminar:Int):Boolean{
    try {
        val sc = Scanner(File(file))
        while (sc.hasNextLine()) {
            var aux=sc.nextLine()
            if(aux!=""){
                if(aux[0].toString().toInt()==indiceEliminar){
                    return true
                }
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    println("No existe ese indice, ingrese de nuevo")
    return false
}