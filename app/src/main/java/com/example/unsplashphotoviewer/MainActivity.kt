package com.example.unsplashphotoviewer

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ViewerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        adapter = ViewerAdapter(this)
        viewPager = findViewById(R.id.pager)
        viewPager.adapter = adapter

        //Тестовая штука
        val kek = UnsplashLoader()
        kek.getData()
    }
}