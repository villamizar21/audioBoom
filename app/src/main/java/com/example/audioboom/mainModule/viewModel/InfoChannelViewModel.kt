package com.example.audioboom.mainModule.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioboom.R
import com.example.audioboom.models.infoChannel.InfoChannel
import com.example.audioboom.mainModule.model.infoChannel.InfoRepository
import kotlinx.coroutines.launch

class InfoChannelViewModel : ViewModel() {

    private val repository = InfoRepository()

    private val result = MutableLiveData<InfoChannel>()
    fun getInfChannel(): LiveData<InfoChannel> = result

    private val snackbarMsg = MutableLiveData<Int>()
    fun getSnackbarMsg() = snackbarMsg

    private val loaded = MutableLiveData<Boolean>()
    fun isLoaded() = loaded

    suspend fun getInfChannelViewModel(id: String) {
        viewModelScope.launch {
            try {
                loaded.value = false
                val resultService = repository.getInfChannel(id)
                result.value = resultService
            } catch (e: Exception) {
                Log.e("", "result del error del servicio---> ${e.message}")
                snackbarMsg.value = R.string.main_error_server
            } finally {
                loaded.value = true
            }
        }
    }
}