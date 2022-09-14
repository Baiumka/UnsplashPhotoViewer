package com.example.unsplashphotoviewer

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ViewerAdapter


    companion object{
        lateinit var viewPager: ViewPager2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        adapter = ViewerAdapter(this)
        viewPager = findViewById(R.id.pager)
        viewPager.adapter = adapter
        viewPager.currentItem = 1
        viewPager.currentItem = 0
    }

    fun onStartClick(view: View)
    {
        adapter.listFragmentListeners.forEach()
        {
            it.onStartClicked()
        }
    }

    fun backButtonClick(view: View)
    {
        viewPager.currentItem = 0
    }
}