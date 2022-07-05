package com.example.audioboom.data.repository.recomendados

import com.example.audioboom.data.models.recommended.Recommended
import com.example.audioboom.data.network.recomendados.RemoteRecommended
import javax.inject.Inject

class RecommendedRepository @Inject constructor(   private val repository: RemoteRecommended) {

    suspend fun getRecommended(): Recommended =
        repository.getRecommended()
}