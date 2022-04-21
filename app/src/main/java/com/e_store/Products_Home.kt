package com.e_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e_store.Services.CustomAdapter
import com.e_store.Services.ItemsViewModel

class Products_Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentPage = "Home"
        setContentView(R.layout.products_home)

        val men_product_list = findViewById<RecyclerView>(R.id.men_product_list)
        val women_product_list = findViewById<RecyclerView>(R.id.women_product_list)

        // this creates a vertical layout Manager
        men_product_list.layoutManager = LinearLayoutManager(this)
        women_product_list.layoutManager = LinearLayoutManager(this)

        men_product_list.apply {
            layoutManager = GridLayoutManager(this.context, 2)
        }
        women_product_list.apply {
            layoutManager = GridLayoutManager(this.context, 2)
        }

        // ArrayList of class ItemsViewModel
        val men_data = ArrayList<ItemsViewModel>()
        val women_data = ArrayList<ItemsViewModel>()

        // Add data to list
        men_data.add(ItemsViewModel(R.drawable.ic_baseline_home_24, "Item 1"))
        men_data.add(ItemsViewModel(R.drawable.ic_baseline_phone_24, "Item 2"))
        men_data.add(ItemsViewModel(R.drawable.ic_baseline_email_24, "Item 3"))
        men_data.add(ItemsViewModel(R.drawable.ic_baseline_home_24, "Item 4"))
        men_data.add(ItemsViewModel(R.drawable.ic_baseline_info_24, "Item 5"))

        women_data.add(ItemsViewModel(R.drawable.ic_baseline_shopping_cart_24, "Item 11"))
        women_data.add(ItemsViewModel(R.drawable.ic_baseline_sports_basketball_24, "Item 22"))
        women_data.add(ItemsViewModel(R.drawable.ic_baseline_home_24, "Item 33"))
        women_data.add(ItemsViewModel(R.drawable.ic_baseline_info_24, "Item 44"))

        // This will pass the ArrayList to our Adapter
        val men_adapter = CustomAdapter(men_data)
        val women_adapter = CustomAdapter(women_data)

        // Setting the Adapter with the recyclerview
        men_product_list.adapter = men_adapter
        women_product_list.adapter = women_adapter
    }
}