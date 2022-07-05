package com.example.audioboom.data.network.infoChannel.playList

import com.example.audioboom.data.apiService.ApiService
import com.example.audioboom.data.models.infoChannel.channelSeleccted.ChannelSelected
import com.example.audioboom.utils.Constans
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteDataPlayList @Inject constructor(private val api: ApiService) {

    suspend fun getPlayList(id: String): ChannelSelected =
        withContext(Dispatchers.IO) {
            api.getPlaylist(id)
        }
}