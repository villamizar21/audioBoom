package com.example.audioboom.mainModule.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioboom.R
import com.example.audioboom.entities.infoChannel.channelSeleccted.ChannelSelected
import com.example.audioboom.mainModule.model.infoChannel.playList.PlayListRepository
import kotlinx.coroutines.launch

class PlayListViewModel : ViewModel() {

    private val repository = PlayListRepository()

    private val result = MutableLiveData<ChannelSelected>()
    fun getPlayList(): LiveData<ChannelSelected> = result

    private val snackbarMsg = MutableLiveData<Int>()
    fun getSnackbarMsg() = snackbarMsg

    suspend fun getPlayListViewModel(id: String) {
        viewModelScope.launch {
            try {
                val resultService = repository.getInfoPlayList(id)
                result.value = resultService
            } catch (e: Exception) {
                Log.e("", "result del error del servicio---> ${e.message}")
                snackbarMsg.value = R.string.main_error_server
            }
        }
    }
}