package com.example.unsplashphotoviewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val linearLayout = view.findViewById<LinearLayout>(R.id.listLayout);

        for(i in 1..50)
        {
            val textView = TextView(view.context)
            textView.text = i.toString()
            textView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            textView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                0.5f
            )
            linearLayout.addView(textView)
        }


        return view;
    }
}