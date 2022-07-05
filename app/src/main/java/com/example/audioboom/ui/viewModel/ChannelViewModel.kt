package com.example.audioboom.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioboom.R
import com.example.audioboom.data.models.channels.AudioClips
import com.example.audioboom.data.repository.channels.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {

    private val result = MutableLiveData<AudioClips>()
    fun getChannels(): LiveData<AudioClips> = result

    private val snackbarMsg = MutableLiveData<Int>()
    fun getSnackbarMsg() = snackbarMsg

    private val loaded = MutableLiveData<Boolean>()
    fun isLoaded() = loaded

    suspend fun getChannelViewModel() {
        viewModelScope.launch {
            try {
                loaded.value = false
                val resultService = repository.getChannels()
                result.value = resultService
            } catch (e: Exception) {
                Log.e("", "result del error del servicio---> ${e.message}")
                snackbarMsg.value = R.string.main_error_server
            }finally {
                loaded.value = true
            }
        }
    }
}