package com.example.audioboom.mainModule.model.infoChannel.playList

import com.example.audioboom.apiService.ApiService
import com.example.audioboom.entities.infoChannel.channelSeleccted.ChannelSelected
import com.example.audioboom.utils.Constans
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataPlayList {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constans.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ApiService::class.java)

    suspend fun getPlayList(id: String): ChannelSelected =
        withContext(Dispatchers.IO) {
            service.getPlaylist(id)
        }
}