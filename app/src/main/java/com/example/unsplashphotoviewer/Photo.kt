package com.example.unsplashphotoviewer
import kotlinx.serialization.*

@Serializable
data class Photo(val id: String, val created_at: String)