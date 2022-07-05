package com.example.audioboom.data.models.infoChannel

data class ChannelInfo(
    val id: Long,
    val channelID: Long,
    val streamID: Long,
    val submissionStyle: String,
    val channelStyle: String,
    val publicRead: Boolean,
    val writable: Boolean,
    val parentChannelID: Any? = null,
    val createdAt: String,
    val updatedAt: String,
    val title: String,
    val description: String,
    val formattedDescription: String,
    val urls: Url,
    val channelClipsCount: Long,
    val totalPlays: Any? = null
)
