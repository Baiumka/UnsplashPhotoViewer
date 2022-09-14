package com.example.unsplashphotoviewer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {


    var listFragmentListeners = ArrayList<IListFragmentListener>()


    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = ListFragment()
                listFragmentListeners += fragment
            }
            1 -> {
                fragment = PhotoFragment()
            }
        }
        return fragment!!
    }


}