package com.example.unsplashphotoviewer

import kotlinx.serialization.*
import kotlinx.serialization.json.*


import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

class UnsplashLoader : IDataLoader {

    private val HOST = "https://api.unsplash.com/";
    private val API_KEY = "ab3411e4ac868c2646c0ed488dfd919ef612b04c264f3374c97fff98ed253dc9";
    private val JsonIgnoreKeys = Json { ignoreUnknownKeys = true }
    private var listeners: List<IDataLoaderListener> = ArrayList()

    fun addListener(newListener : IDataLoaderListener)
    {
        listeners += newListener
    }

    fun removeListener(oldListener : IDataLoaderListener)
    {
        if(listeners.contains(oldListener))
            listeners -= oldListener
    }

    override fun getData() {
        Thread {
            try {
                val urlString = "$HOST/photos?client_id=$API_KEY&page=1"
                val url = URL(urlString)
                with(url.openConnection() as HttpURLConnection) {
                    requestMethod = "GET"
                    if (responseCode == 200) {
                        val content = inputStream.readBytes().toString(Charset.defaultCharset())
                        val resultList = JsonIgnoreKeys.decodeFromString<List<Photo>>(content)
                        sendAnswer(resultList)
                    
                    } else {
                        sendError("RESPONSE CODE: $responseCode")
                    }
                }
            } catch (e: Exception) {
                sendError("Exeption: ${e.message}")
            }

        }.start()
    }

    private fun sendAnswer(photoList: List<Photo>) {
        for (listener in listeners)
        {
            listener.onReceiveNewData(photoList)
        }
    }

    private fun sendError(message: String) {
        for (listener in listeners)
        {
            listener.onGetError(message)
        }
    }


}