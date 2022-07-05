package com.example.audioboom.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioboom.R
import com.example.audioboom.data.models.infoChannel.channelSeleccted.ChannelSelected
import com.example.audioboom.data.repository.infoChannel.playList.PlayListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayListViewModel @Inject constructor(private val repository: PlayListRepository): ViewModel() {

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