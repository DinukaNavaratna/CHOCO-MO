package com.e_store.Fragments

import android.content.Intent
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
                val intent = Intent(getActivity(), Login::class.java)
                intent.putExtra("keyIdentifier", "value")
                getActivity()?.startActivity(intent)
            })

            if(header_title != "Home"){
                HeaderLeftIcon.visibility = View.VISIBLE
                HeaderLeftIcon.setOnClickListener(View.OnClickListener {
                    val intent = Intent(getActivity(), Login::class.java)
                    intent.putExtra("keyIdentifier", "value")
                    getActivity()?.startActivity(intent)
                })
            }
        }
        return view
    }
}