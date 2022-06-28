package com.example.audioboom.mainModule.model.recomendados

import com.example.audioboom.apiService.ApiService
import com.example.audioboom.models.recommended.Recommended
import com.example.audioboom.utils.Constans
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteRecommended {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constans.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ApiService::class.java)

    suspend fun getRecommended(): Recommended =
        withContext(Dispatchers.IO) {
            service.getRecomended()
        }
}