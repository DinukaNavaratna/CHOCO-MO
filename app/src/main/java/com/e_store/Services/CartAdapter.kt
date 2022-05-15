package com.e_store.Services

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.e_store.R
import com.squareup.picasso.Picasso

class CartAdapter(val context: Context) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    lateinit var sp: SharedPreference
    lateinit var cart_list: Array<String>

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_card_view, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val cart_item_string = cart_list[position]
        try {
            var cart_item = cart_item_string.split("^").toTypedArray()

            Picasso.get().load(cart_item[0]).into(holder.imageView);
            holder.product_name.text = cart_item[1]
            holder.product_price.text = "රු " + cart_item[2]
        } catch (e: Exception) {
            Log.e("Cart item exception", e.toString())
            Log.e("Cart item exception", cart_item_string)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        sp = SharedPreference(context)
        var cart_list_string = sp.getPreference("cart_list")

        if(cart_list_string == null){
            return 0
        } else {
            cart_list = cart_list_string.split("|").toTypedArray()
            return cart_list.size
        }
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.cart_product_image)
        val product_name: TextView = itemView.findViewById(R.id.cart_product_name)
        val product_price: TextView = itemView.findViewById(R.id.cart_product_price)
    }
}