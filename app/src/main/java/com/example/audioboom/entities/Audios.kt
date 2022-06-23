package com.example.audioboom.entities

data class Audios(
    val id: Long,
    val season_number: Long,
    val episode_number: Long,
    val title: String,
    val description: String,
    val channel: Channel,
    val urls: Urls

)

data class Urls (
    val detail: String,
    val high_mp3: String,
    val image: String,
)
