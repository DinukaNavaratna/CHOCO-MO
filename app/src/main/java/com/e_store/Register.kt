package com.e_store

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.e_store.Services.Pop_Alert
import com.e_store.Services.SharedPreference
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentPage = "Register"
        setContentView(R.layout.register)

        auth = Firebase.auth

        val register_username = findViewById<EditText>(R.id.register_username)
        val register_email = findViewById<EditText>(R.id.register_email)
        val register_password1 = findViewById<EditText>(R.id.register_password1)
        val register_password2 = findViewById<EditText>(R.id.register_password2)
        var register_login = findViewById<TextView>(R.id.register_login)
        var register_button = findViewById<TextView>(R.id.register_button)

        register_login.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        })

        register_button.setOnClickListener(View.OnClickListener {
            val username = register_username.text.toString()
            val email = register_email.text.toString()
            val password1 = register_password1.text.toString()
            val password2 = register_password2.text.toString()

            var popAlert = Pop_Alert(this, this)

            if(username == "" || email == "" || password1 == "" || password2 == ""){
                popAlert.showAlert("Opzzz!", "All the fields should be filled properly", false, null)
            }
            else if(password1.length > 5){
                popAlert.showAlert("Opzzz!", "Password must have at least 6 characters", false, null)
            }
            else if(password1 != password2){
                popAlert.showAlert("Opzzz!", "Password confirmation failed", false, null)
            } else if (!Application().validateEmail(email)){
                popAlert.showAlert("Opzzz!", "Email not valid", false, null)
            } else {
                auth.createUserWithEmailAndPassword(email, password1)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val sp = SharedPreference(this)
                            sp.setPreference("isLoggedIn", "true")
                            val user_email = auth.currentUser?.email
                            sp.setPreference("user_email", user_email)
                            val intent = Intent(this, Products_Home::class.java)
                            popAlert.showAlert("Yayyy!", "Registration successful", true, intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            popAlert.showAlert("Opzzz!", "Authentication failed", false, null)
                        }
                    }
            }
        })
    }
}