package com.example.audioboom.models.infoChannel.channelSeleccted

data class ChannelSelected(
    val window: Long,
    val version: Long,
    val timestamp: Long,
    val api_warning: String,
    val body: Body
)