package com.example.audioboom.entities.infoChannel.channelSeleccted

import com.example.audioboom.entities.channels.Channel

data class AudioClipsSelected(
    val id: Long,
    val season_number: Long,
    val episode_number: Long,
    val title: String,
    val description: String,
    val channel: Channel,
    val urls: Url
)
