package com.e_store

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.e_store.Services.Pop_Alert
import com.e_store.Services.SharedPreference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


class Checkout : AppCompatActivity() {

    lateinit var sp: SharedPreference
    lateinit var name: String
    lateinit var address: String
    lateinit var phone: String
    lateinit var email: String
    lateinit var checkout_feedback_image: ImageView
    lateinit var checkout_feedback: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentPage = "Checkout"
        setContentView(R.layout.checkout)

        sp = SharedPreference(this)

        var checkout_name = findViewById<EditText>(R.id.checkout_name)
        var checkout_address = findViewById<EditText>(R.id.checkout_address)
        var checkout_phone = findViewById<EditText>(R.id.checkout_phone)
        var checkout_email = findViewById<EditText>(R.id.checkout_email)
        var checkout_back = findViewById<Button>(R.id.checkout_back)
        var checkout_proceed = findViewById<Button>(R.id.checkout_proceed)
        var checkout_payment_options = findViewById<RadioGroup>(R.id.checkout_payment_options)
        checkout_feedback_image = findViewById<ImageView>(R.id.checkout_feedback_image)
        checkout_feedback = findViewById<FrameLayout>(R.id.checkout_feedback)

        checkout_email.setText(sp.getPreference("user_email"))

        checkout_back.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        })

        var popAlert = Pop_Alert(this, this)
        checkout_proceed.setOnClickListener(View.OnClickListener {
            name = checkout_name.text.toString()
            address = checkout_address.text.toString()
            phone = checkout_phone.text.toString()
            email = checkout_email.text.toString()

            if(name != "" && address != "" && phone != "" && email != "") {
                val selectedId: Int = checkout_payment_options.getCheckedRadioButtonId()
                if (selectedId != -1) {
                    val checkout_payment_option =
                        (findViewById<View>(selectedId) as RadioButton).getText()

                    if (checkout_payment_option == "Pay Online") {

                    }
                    saveToDB()
                } else {
                    popAlert.showAlert("Opzzz!", "Please select a payment method", false, null)
                }
            } else {
                popAlert.showAlert("Opzzz!", "All the fields should be filled properly", false, null)
            }
        })
    }

    fun saveToDB(){
        var cart_list_string = sp.getPreference("cart_list")
        var cart_list = cart_list_string.split("|").toTypedArray()

        val db = Firebase.firestore
        val orders = db.collection("Orders")

        // Add a new document with a generated id.
        val order = hashMapOf(
            "name" to name,
            "address" to address,
            "phone" to phone,
            "email" to email,
            "total" to sp.getPreference("cart_total"),
            "timestamp" to (java.sql.Timestamp(System.currentTimeMillis())).toString()
        )

        orders.add(order).addOnSuccessListener { documentReference ->
            Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")

            for(cart_item in cart_list) {
                var items = cart_item.split("^").toTypedArray()

                val item = hashMapOf(
                    "product" to items[1].toString(),
                    "price" to items[2].toString(),
                    "qty" to "1"
                )
                orders.document(documentReference.id).collection("items").add(item)
                    .addOnSuccessListener { documentReference ->
                    }.addOnFailureListener { e ->
                    Log.w(TAG, "Error adding item - "+items[1].toString()+"\n", e)
                }
            }
            feedback(true)
        } .addOnFailureListener { e ->
            Log.w(TAG, "Error adding document", e)
            feedback(false)
        }
    }

    fun feedback(feedback: Boolean){
        var intent1: Intent
        if(feedback){
            Glide.with(this).load(R.drawable.checkout_success).into(checkout_feedback_image)
            sp.removePreference("cart_list")
            sp.removePreference("cart_total")
            intent1 = Intent(this, Products_Home::class.java)
        } else {
            Glide.with(this).load(R.drawable.checkout_failed)
                .into(checkout_feedback_image)
            intent1 = Intent(this, Checkout::class.java)
        }
        checkout_feedback.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent1)
        }, 10000)
    }
}