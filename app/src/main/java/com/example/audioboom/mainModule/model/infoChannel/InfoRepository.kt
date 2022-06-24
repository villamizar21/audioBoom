package com.example.audioboom.mainModule.model.infoChannel

import com.example.audioboom.models.infoChannel.InfoChannel

class InfoRepository {

    private val repository = RemoteDataInfo()

    suspend fun getInfChannel(id: String): InfoChannel =
        repository.getChannels(id)

}