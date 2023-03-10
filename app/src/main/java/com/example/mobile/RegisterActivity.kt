package com.example.mobile

import android.content.Intent
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mobile.data.User
import com.example.mobile.db.MovieDatabase

class RegisterActivity : AppCompatActivity() {
    lateinit var db: MovieDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        db = MovieDatabase(this)

        val editName = findViewById<EditText>(R.id.editName)
        val editEmail = findViewById<EditText>(R.id.editEmail)
        val editPassword = findViewById<EditText>(R.id.editPassword)
        val editConfirm = findViewById<EditText>(R.id.editConfirm)
        val tvError = findViewById<TextView>(R.id.tvError)
        val register = findViewById<Button>(R.id.register)


        register.setOnClickListener {
            tvError.visibility = View.INVISIBLE
            tvError.text = ""

            val name = editName.text.toString()
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            val passwordConfirm = editConfirm.text.toString()

            //verifier
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
                tvError.text =
                    getString(R.string.Error) // text renseigné depsuis le fichier string
                tvError.visibility = View.VISIBLE
            } else {
                //verifier si le saisi du mp correspond
                if (password != passwordConfirm) {
                    tvError.text =
                        getString(R.string.ErrorPassword) // text renseigné depuis le fichier string
                    tvError.visibility = View.VISIBLE
                } else {
                    val user = User(0, name, email, password)
                    val isInserted = db.addUser(user) // inserer dans la bd
                    if (isInserted) {
                        Toast.makeText(this, getString(R.string.succesCount), Toast.LENGTH_LONG).show()
                        Intent(this, HomeActivity::class.java).also {
                            it.putExtra("eamail", email)
                            startActivity(it)
                        }
                    }
                }
            }

        }
    }
}