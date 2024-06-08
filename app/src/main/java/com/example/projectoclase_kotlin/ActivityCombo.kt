package com.example.projectoclase_kotlin

import Configuracion.Personas
import Configuracion.SQLiteConexion
import Configuracion.Trans
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityCombo : AppCompatActivity() {
    private var conexion: SQLiteConexion? = null
    private var combopersonas: Spinner? = null
    private var nombres: EditText? = null
    private var apellidos: EditText? = null
    private var correo: EditText? = null
    private var lista: ArrayList<Personas>? = null
    private var Arreglo: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_combo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById<View>(R.id.main)) { v: View, insets: WindowInsetsCompat ->
            val systemBars: Insets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // CONEXION A BD
        conexion = SQLiteConexion(this, Trans.DBname, null, Trans.Version)
        combopersonas = findViewById(R.id.spinner)
        nombres = findViewById(R.id.cbnombre)
        apellidos = findViewById(R.id.cbapellido)
        correo = findViewById(R.id.cbcorreo)

        ObtenerInfo()
    }

    private fun ObtenerInfo() {
        val db = conexion!!.readableDatabase
        lista = ArrayList()

        val cursor = db.rawQuery(Trans.SelectAllPerson, null)

        while (cursor.moveToNext()) {
            val persona = Personas(
                id = cursor.getInt(0),
                nombres = cursor.getString(1),
                apellidos = cursor.getString(2),
                correo = cursor.getString(4)
            )
            lista!!.add(persona)
        }

        cursor.close()
        FillData()
    }

    private fun FillData() {
        Arreglo = ArrayList()
        for (persona in lista!!) {
            Arreglo!!.add("${persona.id} ${persona.nombres} ${persona.apellidos} ${persona.correo}")
        }

        val adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item,
            Arreglo!!
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        combopersonas!!.adapter = adapter

        combopersonas!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedPerson: Personas = lista!![position]
                nombres!!.setText(selectedPerson.nombres)
                apellidos!!.setText(selectedPerson.apellidos)
                correo!!.setText(selectedPerson.correo)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}
