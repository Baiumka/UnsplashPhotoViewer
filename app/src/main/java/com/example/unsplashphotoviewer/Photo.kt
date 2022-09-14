package com.example.unsplashphotoviewer
import kotlinx.serialization.*

@Serializable
data class Photo(val id: String, val likes: Int, val user: User, val urls: Urls)