package com.example.unsplashphotoviewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class PhotoFragment : Fragment(), IPhotoClickListener {

    lateinit var mainImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_photo, container, false)
        ListFragment.mainListFragment!!.addListener(this)
        mainImageView = view.findViewById<ImageView>(R.id.mainImage);

        return view;
    }

    override fun onPhotoClick(fullUrl: String) {
        Picasso.get().load(fullUrl).into(mainImageView);
    }


}