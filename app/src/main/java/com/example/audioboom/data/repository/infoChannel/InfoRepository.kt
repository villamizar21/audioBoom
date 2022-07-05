package com.example.audioboom.data.repository.infoChannel

import com.example.audioboom.data.models.infoChannel.InfoChannel
import com.example.audioboom.data.network.infoChannel.RemoteDataInfo
import javax.inject.Inject

class InfoRepository @Inject constructor(private val repository: RemoteDataInfo){

    suspend fun getInfChannel(id: String): InfoChannel =
        repository.getChannels(id)

}