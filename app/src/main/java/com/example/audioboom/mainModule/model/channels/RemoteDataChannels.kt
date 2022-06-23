package com.example.audioboom.mainModule.model.channels

import com.example.audioboom.apiService.ApiService
import com.example.audioboom.entities.AudioClips
import com.example.audioboom.utils.Constans
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataChannels {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constans.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ApiService::class.java)

    suspend fun getChannels(): AudioClips =
        withContext(Dispatchers.IO) {
            service.getallaudios()
        }
}