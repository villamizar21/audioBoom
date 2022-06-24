package com.example.audioboom.models.channels

data class AudioClips(
    val window:String,
    val version:String,
    val timestamp:String,
    val api_warning:String,
    val body: Body,
)
