package com.example.audioboom.mainModule.model.channels

import com.example.audioboom.entities.AudioClips

class MainRepository {
    private val repository = RemoteDataChannels()

    suspend fun getChannels(): AudioClips =
        repository.getChannels()
}