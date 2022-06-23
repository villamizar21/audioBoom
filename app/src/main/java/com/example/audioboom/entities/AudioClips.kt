package com.example.audioboom.entities

data class AudioClips(
    val window:String,
    val version:String,
    val timestamp:String,
    val api_warning:String,
    val body:Body,
)
