package com.e_store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e_store.Services.CartAdapter
import com.e_store.Services.CustomAdapter
import com.e_store.Services.SharedPreference

class Cart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentPage = "Cart"
        setContentView(R.layout.cart)

        val sp = SharedPreference(this)

        var cart_clear_cart = findViewById<Button>(R.id.cart_clear_cart)
        var cart_checkout = findViewById<Button>(R.id.cart_checkout)
        var cart_summary = findViewById<ConstraintLayout>(R.id.cart_summary)

        var cart_list_string = sp.getPreference("cart_list")
        var cart_total_value_string = sp.getPreference("cart_total")

        if(cart_list_string == null || cart_total_value_string == null){
            cart_checkout.isEnabled = false
            cart_summary.visibility = View.GONE
            Toast.makeText(this, "No items in the cart!", Toast.LENGTH_SHORT).show()
        } else {
            val cart_list_view = findViewById<RecyclerView>(R.id.cart_list_view)
            cart_list_view.layoutManager = LinearLayoutManager(this)
            val cart_list_adapter = CartAdapter(this)
            cart_list_view.adapter = cart_list_adapter

            cart_checkout.setOnClickListener(View.OnClickListener {
                val intent = Intent(this, Checkout::class.java)
                startActivity(intent)
            })
        }
        cart_clear_cart.setOnClickListener(View.OnClickListener {
            sp.removePreference("cart_list")
            sp.removePreference("cart_total")
            val intent = Intent(this, Products_Home::class.java)
            startActivity(intent)
        })
    }
}