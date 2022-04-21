package com.e_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.animation.Animator

import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import com.e_store.Services.SharedPreference

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentPage = "Register"
        setContentView(R.layout.register)

        var register_login = findViewById<TextView>(R.id.register_login)
        var register_button = findViewById<TextView>(R.id.register_button)

        register_login.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        })

        register_button.setOnClickListener(View.OnClickListener {
            val sp = SharedPreference(this)
            sp.setPreference("isLoggedIn", "true")
            val intent = Intent(this, Products_Home::class.java)
            startActivity(intent)
        })
    }
}