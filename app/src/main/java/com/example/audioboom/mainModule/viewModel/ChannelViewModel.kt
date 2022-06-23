package com.example.audioboom.mainModule.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioboom.R
import com.example.audioboom.entities.AudioClips
import com.example.audioboom.mainModule.model.channels.MainRepository
import kotlinx.coroutines.launch

class ChannelViewModel : ViewModel() {

    private val repository = MainRepository()

    private val result = MutableLiveData<AudioClips>()
    fun getChannels(): LiveData<AudioClips> = result

    private val snackbarMsg = MutableLiveData<Int>()
    fun getSnackbarMsg() = snackbarMsg

    suspend fun getChannelViewModel() {
        viewModelScope.launch {
            try {
                val resultService = repository.getChannels()
                result.value = resultService
            } catch (e: Exception) {
                Log.e("", "result del error del servicio---> ${e.message}")
                snackbarMsg.value = R.string.main_error_server
            }
        }
    }
}