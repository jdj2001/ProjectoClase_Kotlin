package com.example.projectoclase_kotlin

import Configuracion.SQLiteConexion
import Configuracion.Trans
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityIni : AppCompatActivity() {
    var nombres: EditText? = null
    var apellidos: EditText? = null
    var edad: EditText? = null
    var correo: EditText? = null
    var btnagregar: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_ini)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById<View>(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nombres = findViewById<View>(R.id.nombres) as EditText
        apellidos = findViewById<View>(R.id.apellido) as EditText
        edad = findViewById<View>(R.id.edad) as EditText
        correo = findViewById<View>(R.id.correo) as EditText

        btnagregar = findViewById<View>(R.id.btningresar) as Button

        btnagregar!!.setOnClickListener { Agregar() }
    }

    private fun Agregar() {
        try {
            val conexion = SQLiteConexion(this, Trans.DBname, null, Trans.Version)
            val db: SQLiteDatabase = conexion.getWritableDatabase()

            val valores = ContentValues()
            valores.put(Trans.nombres, nombres!!.text.toString())
            valores.put(Trans.apellidos, apellidos!!.text.toString())
            valores.put(Trans.edad, edad!!.text.toString())
            valores.put(Trans.correo, correo!!.text.toString())
            //INSERTANDO
            val resultado = db.insert(Trans.TablePersonas, Trans.id, valores)

            Toast.makeText(
                applicationContext,
                "Registro ingresado con exito$resultado",
                Toast.LENGTH_LONG
            ).show()

            db.close()
        } catch (ex: Exception) {
            ex.toString()
        }
        //Toast.makeText(this, "Hola", Toast.LENGTH_LONG).show();
    }
}