package com.e_store.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.e_store.R
import com.e_store.Services.SharedPreference

class Cart_Summary : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.cart_summary, container, false)

        val sp = SharedPreference(this.context)
        var cart_total_value_string = sp.getPreference("cart_total")
        var cart_subtotal = view.findViewById<TextView>(R.id.cart_subtotal)
        var cart_total = view.findViewById<TextView>(R.id.cart_total)

        if(cart_total_value_string != null){
            var cart_total_value = cart_total_value_string.toFloat()
            cart_subtotal.text = "රු "+String.format("%.2f", cart_total_value).toString()
            cart_total.text = "රු "+("%.2f".format((cart_total_value+500))).toString()
        }

        return view
    }
}