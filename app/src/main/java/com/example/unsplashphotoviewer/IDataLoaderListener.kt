package com.example.unsplashphotoviewer

interface IDataLoaderListener {
    fun onReceiveNewData(photos : List<Photo>)
    fun onGetError(message : String)
}