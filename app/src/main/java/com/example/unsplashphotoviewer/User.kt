package com.example.unsplashphotoviewer
import kotlinx.serialization.Serializable

@Serializable
data class User(val id: String, val username: String)