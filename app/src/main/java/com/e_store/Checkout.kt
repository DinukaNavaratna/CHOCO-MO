package com.e_store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.e_store.Services.SharedPreference


class Checkout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentPage = "Checkout"
        setContentView(R.layout.checkout)

        val sp = SharedPreference(this)

        var checkout_name = findViewById<EditText>(R.id.checkout_name)
        var checkout_address = findViewById<EditText>(R.id.checkout_address)
        var checkout_phone = findViewById<EditText>(R.id.checkout_phone)
        var checkout_email = findViewById<EditText>(R.id.checkout_email)
        var checkout_back = findViewById<Button>(R.id.checkout_back)
        var checkout_proceed = findViewById<Button>(R.id.checkout_proceed)
        var checkout_payment_options = findViewById<RadioGroup>(R.id.checkout_payment_options)
        var checkout_feedback_image = findViewById<ImageView>(R.id.checkout_feedback_image)
        var checkout_feedback = findViewById<FrameLayout>(R.id.checkout_feedback)

        checkout_back.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        })

        checkout_proceed.setOnClickListener(View.OnClickListener {
            var intent: Intent = Intent(this, Products_Home::class.java)
            val selectedId: Int = checkout_payment_options.getCheckedRadioButtonId()
            if(selectedId != -1) {
                val checkout_payment_option = (findViewById<View>(selectedId) as RadioButton).getText()

                if (checkout_payment_option == "Pay Online") {

                }

                Glide.with(this).load(R.drawable.checkout_success).into(checkout_feedback_image)

                sp.removePreference("cart_list")
                sp.removePreference("cart_total")
            } else {
                Glide.with(this).load(R.drawable.checkout_failed).into(checkout_feedback_image)
                intent = Intent(this, Checkout::class.java)
            }

            checkout_feedback.visibility = View.VISIBLE

            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(intent)
            }, 10000)
        })
    }
}