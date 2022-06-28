package com.example.audioboom.mainModule.model.popular

import com.example.audioboom.models.channels.AudioClips

class PopularRepository {
    private val repository = RemotePopular()

    suspend fun getPopular(): AudioClips =
        repository.getPopular()
}