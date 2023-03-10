package com.example.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class ProfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        val textView2 = findViewById<TextView>(R.id.textView2)
        val textView3 = findViewById<TextView>(R.id.textView3)
        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val textView4 = findViewById<TextView>(R.id.textView4)

        val email = intent.getStringExtra("email")
        val nom = intent.getStringExtra("nom")
        val prenom = intent.getStringExtra("prenom")
        val age = intent.getStringExtra("age")
        //val image = intent.getIntExtra("image")

       // imageView3.imageAlpha = image
        textView2.text = email
        textView3.text = nom
        textView2.text = prenom
        textView4.text = age
    }
}
