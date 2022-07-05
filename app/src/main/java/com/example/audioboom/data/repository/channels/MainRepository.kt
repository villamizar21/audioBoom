package com.example.audioboom.data.repository.channels

import com.example.audioboom.data.models.channels.AudioClips
import com.example.audioboom.data.network.channels.RemoteDataChannels
import javax.inject.Inject

class MainRepository @Inject constructor( private val repository: RemoteDataChannels){

    suspend fun getChannels(): AudioClips =
        repository.getChannels()
}