package com.e_store.Fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.wifi.hotspot2.pps.HomeSp
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.e_store.*
import com.e_store.Services.Pop_Alert
import com.e_store.Services.SharedPreference
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Header : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.header, container, false)

        var HeaderLeftIcon: ImageView = view.findViewById(R.id.HeaderLeftIcon)
        var HeaderRightIcon: ImageView = view.findViewById(R.id.HeaderRightIcon)
        var HeaderTitle: TextView = view.findViewById(R.id.HeaderTitle)

        var app = Application()
        val header_title = app.getCurrentPage()
        HeaderTitle.setText(header_title)

        var popAlert = activity?.let { it1 -> Pop_Alert(it1, it1) }

        if (header_title == "Login" || header_title == "Register"){
            HeaderRightIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_info_24))
            HeaderRightIcon.setOnClickListener(View.OnClickListener {
                popAlert?.showAlert("Hello!", "This app is under development. Please be in touch for updates...", false, null)
            })
        } else {
            HeaderRightIcon.setOnClickListener(View.OnClickListener {
                val intent = Intent(activity, Cart::class.java)
                activity?.startActivity(intent)
            })

            HeaderLeftIcon.visibility = View.VISIBLE
            if(header_title != "Home"){
                HeaderLeftIcon.setOnClickListener(View.OnClickListener {
                    val intent = Intent(activity, Products_Home::class.java)
                    activity?.startActivity(intent)
                })
            } else {
                HeaderLeftIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_person_24))
                HeaderLeftIcon.setOnClickListener(View.OnClickListener {
                    val intent = Intent(activity, Login::class.java)
                    popAlert?.showAlert("Bye!", "You will be logged out from your account.", true, intent)
                    var sp = SharedPreference(activity)
                    sp.clearPreference()
                    Firebase.auth.signOut()
                })
            }
        }
        return view
    }
}