package com.example.unsplashphotoviewer

interface IDataLoader {
    fun getData()
    fun addListener(listener: IDataLoaderListener)
    fun removeListener(listener: IDataLoaderListener)
}