package com.example.audioboom.apiService

import com.example.audioboom.models.channels.AudioClips
import com.example.audioboom.models.infoChannel.InfoChannel
import com.example.audioboom.models.infoChannel.channelSeleccted.ChannelSelected
import com.example.audioboom.utils.Constans
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(Constans.AUDIO)
    suspend fun getallaudios(): AudioClips

    @GET(Constans.AUDIO+Constans.POPULAR)
    suspend fun getPopularaudios(): AudioClips

    @GET(Constans.CHANNELS+"/{id}")
    suspend fun getInfChannel(@Path("id") id: String): InfoChannel

    @GET(Constans.CHANNELS+"/{id}/"+Constans.AUDIO)
    suspend fun getPlaylist(@Path("id") id: String): ChannelSelected
}