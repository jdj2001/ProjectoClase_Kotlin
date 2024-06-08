package Configuracion

object Trans {
    //VERSION DB
    const val Version: Int = 1

    //nombre BD
    const val DBname: String =
        "PM012P" //constante, no se puede modificar a menos que se sobreescriba

    //consume muchos recursos de memoria
    //tabla personas
    const val TablePersonas: String = "personas"

    //propiedades
    const val id: String = "id"
    const val nombres: String = "nombres"
    const val apellidos: String = "apellidos"
    const val edad: String = "edad"
    const val correo: String = "correo"
    const val foto: String = "foto"

    // ELEMENTOS DDL PARA CREAR OBJETOS DE BD
    //CREAR TABLA
    const val CreateTablePersonas: String =
        "CREATE TABLE " + TablePersonas + " ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, nombres TEXT, apellidos TEXT, edad INTEGER, correo TEXT, foto TEXT )"

    const val SelectAllPerson: String = "SELECT * FROM " + TablePersonas

    const val DropTablePersonas: String = "DROP TABLE IF EXISTS " + TablePersonas
}