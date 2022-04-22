package com.e_store.Fragments

import android.content.Intent
import android.net.wifi.hotspot2.pps.HomeSp
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.e_store.*

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

        if (header_title == "Login" || header_title == "Register"){
            HeaderRightIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_info_24))
            HeaderRightIcon.setOnClickListener(View.OnClickListener {
            })
        } else {
            HeaderRightIcon.setOnClickListener(View.OnClickListener {
                val intent = Intent(getActivity(), Cart::class.java)
                getActivity()?.startActivity(intent)
            })

            HeaderLeftIcon.visibility = View.VISIBLE
            if(header_title != "Home"){
                HeaderLeftIcon.setOnClickListener(View.OnClickListener {
                    val intent = Intent(getActivity(), Products_Home::class.java)
                    getActivity()?.startActivity(intent)
                })
            } else {
                HeaderLeftIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_person_24))
                HeaderLeftIcon.setOnClickListener(View.OnClickListener {
                    val intent = Intent(getActivity(), Login::class.java)
                    getActivity()?.startActivity(intent)
                })
            }
        }
        return view
    }
}