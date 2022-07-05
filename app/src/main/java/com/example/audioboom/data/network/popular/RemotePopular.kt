package com.example.audioboom.data.network.popular

import com.example.audioboom.data.apiService.ApiService
import com.example.audioboom.data.models.channels.AudioClips
import com.example.audioboom.utils.Constans
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemotePopular @Inject constructor(private val api: ApiService) {

    suspend fun getPopular(): AudioClips =
        withContext(Dispatchers.IO) {
            api.getPopularaudios()
        }
}