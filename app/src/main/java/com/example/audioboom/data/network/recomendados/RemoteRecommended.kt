package com.example.audioboom.data.network.recomendados

import com.example.audioboom.data.apiService.ApiService
import com.example.audioboom.data.models.recommended.Recommended
import com.example.audioboom.utils.Constans
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteRecommended @Inject constructor(private val api: ApiService) {

    suspend fun getRecommended(): Recommended =
        withContext(Dispatchers.IO) {
            api.getRecomended()
        }
}