fun main(args: Array<String>) {
    //Variables

    println("---------------------------")
    println("| 1. Crear                |")
    println("---------------------------")
    println("| 2. Ver                  |")
    println("---------------------------")
    println("| 3. Eliminar             |")
    println("---------------------------")
    println("| 4. Actualizar           |")
    println("---------------------------")
    print("Ingrese el numero de la opcion que desea: ")
    val opcion=readln()
    println(opcion)
    println("* Escoga la opcion que desea: *")

    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}