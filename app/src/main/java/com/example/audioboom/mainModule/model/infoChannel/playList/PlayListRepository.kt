package com.example.audioboom.mainModule.model.infoChannel.playList

import com.example.audioboom.entities.infoChannel.channelSeleccted.ChannelSelected

class PlayListRepository {

    private val repository = RemoteDataPlayList()

    suspend fun getInfoPlayList(id: String): ChannelSelected =
        repository.getPlayList(id)

}