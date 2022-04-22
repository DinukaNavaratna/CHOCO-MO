package com.e_store.Services

import android.content.Context
import android.util.Log
import android.util.Log.INFO
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.recyclerview.widget.RecyclerView
import com.e_store.R
import java.util.logging.Level.INFO

class CustomAdapter(private val mList: List<ItemsViewModel>, val context: Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    lateinit var sp: SharedPreference

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.products_home_card_view, parent, false)

        sp = SharedPreference(context)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        Log.i("Items", position.toString()+" | "+ItemsViewModel.name+" | "+ItemsViewModel.price)
        holder.imageView.setImageResource(ItemsViewModel.image)
        holder.product_name.text = ItemsViewModel.name
        holder.product_price.text = "රු "+ItemsViewModel.price
        holder.add_to_cart.setOnClickListener(View.OnClickListener {
            var cart_list = sp.getPreference("cart_list")
            if(cart_list == null){
                cart_list = Integer.toString(ItemsViewModel.image)+":"+ItemsViewModel.name+":"+ItemsViewModel.price
            } else {
                cart_list += "|"+Integer.toString(ItemsViewModel.image)+":"+ItemsViewModel.name+":"+ItemsViewModel.price
            }
            sp.setPreference("cart_list", cart_list)
            var cart_total_value_string = sp.getPreference("cart_total")
            var cart_total_value: Float
            var price = String.format("%.2f", ((ItemsViewModel.price).replace(",", "")).toFloat()).toFloat()
            if(cart_total_value_string == null){
                cart_total_value = price
            } else {
                cart_total_value = String.format("%.2f", cart_total_value_string.toFloat()).toFloat()
                cart_total_value += price
            }
            sp.setPreference("cart_total", cart_total_value.toString())
            Toast.makeText(context, "Item added to cart!", Toast.LENGTH_SHORT).show()
        })
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.product_image)
        val product_name: TextView = itemView.findViewById(R.id.product_name)
        val product_price: TextView = itemView.findViewById(R.id.product_price)
        val add_to_cart: ImageView = itemView.findViewById(R.id.add_to_cart)
    }
}