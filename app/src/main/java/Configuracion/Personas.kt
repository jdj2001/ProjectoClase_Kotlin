package Configuracion

class Personas(
    var id: Int? = null,
    var nombres: String? = null,
    var apellidos: String? = null,
    var edad: Int? = null,
    var correo: String? = null,
    var foto: String? = null
) {
    // Constructor vacío
    constructor() : this(null, null, null, null, null, null)
}
