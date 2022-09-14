package com.example.unsplashphotoviewer

import kotlinx.serialization.Serializable

@Serializable
data class Urls(val full: String, val small: String)