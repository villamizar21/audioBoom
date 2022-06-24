package com.example.audioboom.models.infoChannel.channelSeleccted

import com.example.audioboom.models.channels.Channel

data class AudioClipsSelected(
    val id: Long,
    val season_number: Long,
    val episode_number: Long,
    val title: String,
    val description: String,
    val channel: Channel,
    val urls: Url
)
