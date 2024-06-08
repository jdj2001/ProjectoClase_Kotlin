package com.example.projectoclase_kotlin

import Configuracion.Personas
import Configuracion.SQLiteConexion
import Configuracion.Trans
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityList : AppCompatActivity() {
    private var conexion: SQLiteConexion? = null
    private var listperson: ListView? = null
    private var lista: ArrayList<Personas>? = null
    private var Arreglo: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_list)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById<View>(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        conexion = SQLiteConexion(this, Trans.DBname, null, Trans.Version)
        listperson = findViewById(R.id.listperson)

        ObtenerInfo()

        val adp: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, Arreglo!!)
        listperson!!.adapter = adp

        listperson!!.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val ElementoSeleccionado = parent.getItemAtPosition(position) as String
                Toast.makeText(applicationContext, ElementoSeleccionado, Toast.LENGTH_LONG).show()
            }
    }

    private fun ObtenerInfo() {
        val db = conexion!!.readableDatabase
        var person: Personas? = null
        lista = ArrayList()

        val cursor = db.rawQuery(Trans.SelectAllPerson, null)

        while (cursor.moveToNext()) {
            person = Personas()
            person.id = cursor.getInt(0)
            person.nombres = cursor.getString(1)
            person.apellidos = cursor.getString(2)
            person.edad = cursor.getInt(3)
            person.id = cursor.getInt(4)
            lista!!.add(person)
        }

        cursor.close()
        FillDate()
    }

    private fun FillDate() {
        Arreglo = ArrayList()
        for (i in lista!!.indices) {
            Arreglo!!.add(
                "${lista!![i].id} ${lista!![i].nombres} ${lista!![i].apellidos}"
            )
        }
    }
}
