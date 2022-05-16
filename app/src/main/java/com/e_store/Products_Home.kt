package com.e_store

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.e_store.Services.CustomAdapter
import com.e_store.Services.ItemsViewModel
import com.e_store.Services.Pop_Alert
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Products_Home : AppCompatActivity() {

    // ArrayList of class ItemsViewModel
    val data_1 = ArrayList<ItemsViewModel>()
    val data_2 = ArrayList<ItemsViewModel>()
    val db = Firebase.firestore
    lateinit var products_home_scroll_view: ScrollView
    lateinit var product_list_1: RecyclerView
    lateinit var product_list_2: RecyclerView
    lateinit var popAlert: Pop_Alert

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentPage = "Home"
        setContentView(R.layout.products_home)

        popAlert = Pop_Alert(this, this)

        products_home_scroll_view = findViewById<ScrollView>(R.id.products_home_scroll_view)
        product_list_1 = findViewById<RecyclerView>(R.id.product_list_1)
        product_list_2 = findViewById<RecyclerView>(R.id.product_list_2)

        // this creates a vertical layout Manager
        object : LinearLayoutManager(this){ override fun canScrollVertically(): Boolean { return false } }
        product_list_1.layoutManager = LinearLayoutManager(this)
        product_list_2.layoutManager = LinearLayoutManager(this)

        product_list_1.apply {
            layoutManager = GridLayoutManager(this.context, 2)
        }
        product_list_2.apply {
            layoutManager = GridLayoutManager(this.context, 2)
        }
        getProductList1()

        var swipeContainer = findViewById<SwipeRefreshLayout>(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener {
            finish();
            startActivity(intent);
        }
    }

    fun getProductList1(){
        db.collection("Products")
            .whereEqualTo("category", "Offer")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "Offer Name => ${document.data["name"]}")
                    data_1.add(ItemsViewModel(document.data["image"].toString(), document.data["name"].toString(), document.data["price"].toString()))
                }
                getProductList2()
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error getting documents: ", exception)
                popAlert.showAlert("Opzzz!", "Offer product loading failed", false, null)
                getProductList2()
            }
    }

    fun getProductList2(){
        db.collection("Products")
            .whereNotEqualTo("category", "Offer")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "Product Name => ${document.data["name"]}")
                    data_2.add(ItemsViewModel(document.data["image"].toString(), document.data["name"].toString(), document.data["price"].toString()))
                }
                loadProducts()
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error getting documents: ", exception)
                popAlert.showAlert("Opzzz!", "All products loading failed", false, null)
                loadProducts()
            }
    }

    fun loadProducts(){
        if(data_1.count() == 0 && data_2.count() == 0){
            popAlert.showAlert("Opzzz!", "Product list is empty", false, null)
        } else {
            // This will pass the ArrayList to our Adapter
            val adapter_1 = CustomAdapter(data_1, this)
            val adapter_2 = CustomAdapter(data_2, this)

            // Setting the Adapter with the recyclerview
            product_list_1.adapter = adapter_1
            product_list_2.adapter = adapter_2

            //products_home_scroll_view.fullScroll(ScrollView.FOCUS_UP)
            products_home_scroll_view.smoothScrollTo(0, 0)
        }
    }
}