package Configuracion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class SQLiteConexion(context: Context?, name: String?, factory: CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        //CREACIÓN DE TODAS LAS TABLAS EN SQLITE //OBJETO DDL PARA CREACION TABLAS
        db.execSQL(Trans.CreateTablePersonas)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(Trans.DropTablePersonas)
        onCreate(db)
    }
}