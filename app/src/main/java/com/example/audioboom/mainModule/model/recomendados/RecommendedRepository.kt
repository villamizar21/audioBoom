package com.example.audioboom.mainModule.model.recomendados

import com.example.audioboom.models.recommended.Recommended

class RecommendedRepository {
    private val repository = RemoteRecommended()

    suspend fun getRecommended(): Recommended =
        repository.getRecommended()
}