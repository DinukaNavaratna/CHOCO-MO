package com.e_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.constraintlayout.widget.ConstraintLayout
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

        val products_home_scroll_view = findViewById<ScrollView>(R.id.products_home_scroll_view)
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
        men_data.add(ItemsViewModel(R.drawable.men_1, "PALMERS Hair Food Formula 150gm", "3,250.00"))
        men_data.add(ItemsViewModel(R.drawable.men_2, "Cocoa Stretch Mark Cream – 125gm", "3,250.00"))
        men_data.add(ItemsViewModel(R.drawable.men_3, "Cocoa Stretch Mark Lotion – 250ml", "2,650.00"))
        men_data.add(ItemsViewModel(R.drawable.men_4, "Cocoa Skin Therapy Oil Roship fragrance 25ml", "855.00"))
        men_data.add(ItemsViewModel(R.drawable.men_5, "Cocoa Body Lotion MEN / Pump 400ml", "3,950.00"))
        men_data.add(ItemsViewModel(R.drawable.men_2, "Cocoa Stretch Mark Cream – 125gm", "3,250.00"))
        men_data.add(ItemsViewModel(R.drawable.men_3, "Cocoa Stretch Mark Lotion – 250ml", "2,650.00"))
        men_data.add(ItemsViewModel(R.drawable.men_4, "Cocoa Skin Therapy Oil Roship fragrance 25ml", "855.00"))
        men_data.add(ItemsViewModel(R.drawable.men_5, "Cocoa Body Lotion MEN / Pump 400ml", "3,950.00"))

        women_data.add(ItemsViewModel(R.drawable.women_1, "PALMERS Coconut Oil Shampoo 400ml", "3,950.00"))
        women_data.add(ItemsViewModel(R.drawable.women_2, "PALMERS Cocoa Butter Body Lotion 400ml", "4,950.00"))
        women_data.add(ItemsViewModel(R.drawable.women_3, "Coconut Oil Moisture Gro 150g", "2,950.00"))
        women_data.add(ItemsViewModel(R.drawable.women_4, "EGYPTIAN MAGIC 30+ Age Miracle Cream 38ml (Egypt)", "4,700.00"))

        // This will pass the ArrayList to our Adapter
        val men_adapter = CustomAdapter(men_data, this)
        val women_adapter = CustomAdapter(women_data, this)

        // Setting the Adapter with the recyclerview
        men_product_list.adapter = men_adapter
        women_product_list.adapter = women_adapter

        //products_home_scroll_view.fullScroll(ScrollView.FOCUS_UP)
        products_home_scroll_view.smoothScrollTo(0,0)
    }
}