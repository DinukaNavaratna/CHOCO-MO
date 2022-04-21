package com.e_store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.e_store.Services.SharedPreference

class Splash_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spalsh_screen)

        val sp = SharedPreference(this)

        Handler(Looper.getMainLooper()).postDelayed({
            var isLoggedIn = sp.getPreference("isLoggedIn")
            if(isLoggedIn == "true"){
                val intent = Intent(this, Products_Home::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }
}