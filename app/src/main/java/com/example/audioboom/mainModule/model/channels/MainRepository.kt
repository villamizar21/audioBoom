package com.example.audioboom.mainModule.model.channels

import com.example.audioboom.models.channels.AudioClips

class MainRepository {
    private val repository = RemoteDataChannels()

    suspend fun getChannels(): AudioClips =
        repository.getChannels()
}