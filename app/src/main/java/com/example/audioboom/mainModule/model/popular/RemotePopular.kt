package com.example.audioboom.mainModule.model.popular

import com.example.audioboom.apiService.ApiService
import com.example.audioboom.models.channels.AudioClips
import com.example.audioboom.utils.Constans
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemotePopular {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constans.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ApiService::class.java)

    suspend fun getPopular(): AudioClips=
        withContext(Dispatchers.IO){
            service.getPopularaudios()
        }
}