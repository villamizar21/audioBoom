package com.example.audioboom.data.repository.infoChannel.playList

import com.example.audioboom.data.models.infoChannel.channelSeleccted.ChannelSelected
import com.example.audioboom.data.network.infoChannel.playList.RemoteDataPlayList
import javax.inject.Inject

class PlayListRepository @Inject constructor(  private val repository: RemoteDataPlayList){

    suspend fun getInfoPlayList(id: String): ChannelSelected =
        repository.getPlayList(id)

}