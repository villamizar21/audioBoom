package com.example.audioboom.models.infoChannel


data class InfoChannel(
    val window:String,
    val version:String,
    val timestamp:String,
    val api_warning:String,
    val body: DetailsChannel,
)
