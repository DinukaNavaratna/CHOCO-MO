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

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentPage = "Login"
        setContentView(R.layout.login)

        var login_register = findViewById<TextView>(R.id.login_register)
        var login_button = findViewById<TextView>(R.id.login_button)

        login_register.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        })

        login_button.setOnClickListener(View.OnClickListener {
            val sp = SharedPreference(this)
            sp.setPreference("isLoggedIn", "true")
            val intent = Intent(this, Products_Home::class.java)
            startActivity(intent)
        })
    }
}