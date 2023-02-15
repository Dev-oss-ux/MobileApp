package com.example.mobile.db
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import com.example.mobile.data.User


class MovieDatabase(mContext : Context) : SQLiteOpenHelper(
    mContext,
    DB_NAME,
    null,
    DB_VERSION
){
    override fun onCreate(db: SQLiteDatabase?) {
      //creation des tables

        val createTableUser = """
            CREATE TABLE users(
            $USER_ID integer PRIMARY KEY,
            $NAME varchars(50),
            $EMAIL varchars(100),
            $PASSWORD varchars(20)
            )
        """.trimIndent()
        db?.execSQL(createTableUser)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db?.execSQL("DROP TABLE IF EXISTS $USERS_TABLE_NAME")
        onCreate(db)
    }

    fun addUser(user: User): Boolean{

        //inserer un nouveau utilisateur
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME, user.name)
        values.put(EMAIL, user.email)
        values.put(NAME, user.password)

        //insert into users(nom, email, password) values(user.nom, user.email, user.password)
        val result = db.insert(USERS_TABLE_NAME, null, values).toInt()

        db.close()

        return result != -1
    }


    companion object {
        private val DB_NAME = "MovieDatabase_db"
        private val DB_VERSION = 1
        private val USERS_TABLE_NAME = "users"
        private val NAME = "name"
        private val USER_ID = "id"
        private val EMAIL = "email"
        private val PASSWORD = "password"
    }
}