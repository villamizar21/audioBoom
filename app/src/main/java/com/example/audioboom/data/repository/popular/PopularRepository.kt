package com.example.audioboom.data.repository.popular

import com.example.audioboom.data.models.channels.AudioClips
import com.example.audioboom.data.network.popular.RemotePopular
import javax.inject.Inject

class PopularRepository @Inject constructor(private val repository: RemotePopular){

    suspend fun getPopular(): AudioClips =
        repository.getPopular()
}