package com.example.audioboom.apiService

import com.example.audioboom.entities.channels.AudioClips
import com.example.audioboom.entities.infoChannel.InfoChannel
import com.example.audioboom.entities.infoChannel.channelSeleccted.ChannelSelected
import com.example.audioboom.utils.Constans
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(Constans.AUDIO)
    suspend fun getallaudios(): AudioClips

    @GET(Constans.CHANNELS+"/{id}")
    suspend fun getInfChannel(@Path("id") id: String): InfoChannel

    @GET(Constans.CHANNELS+"/{id}/"+Constans.AUDIO)
    suspend fun getPlaylist(@Path("id") id: String): ChannelSelected
}