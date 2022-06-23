package com.example.audioboom.entities

data class Channel(
    val id: Long,
    val title: String,
    val urls: Url
)

data class Url(
    val details: String,
    val logo_image: LogoImage,
)

data class LogoImage(
    val original: String
)
