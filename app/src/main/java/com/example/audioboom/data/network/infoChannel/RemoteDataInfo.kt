package com.example.audioboom.data.network.infoChannel

import com.example.audioboom.data.apiService.ApiService
import com.example.audioboom.data.models.infoChannel.InfoChannel
import com.example.audioboom.utils.Constans
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteDataInfo @Inject constructor(private val api: ApiService) {

    suspend fun getChannels(id: String): InfoChannel =
        withContext(Dispatchers.IO) {
            api.getInfChannel(id)
        }
}