package com.example.audioboom.mainModule.model.infoChannel

import com.example.audioboom.apiService.ApiService
import com.example.audioboom.models.infoChannel.InfoChannel
import com.example.audioboom.utils.Constans
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataInfo {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constans.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ApiService::class.java)

    suspend fun getChannels(id: String): InfoChannel =
        withContext(Dispatchers.IO) {
            service.getInfChannel(id)
        }
}