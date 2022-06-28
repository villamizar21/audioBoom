package com.example.audioboom.mainModule.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioboom.R
import com.example.audioboom.mainModule.model.popular.PopularRepository
import com.example.audioboom.models.channels.AudioClips
import kotlinx.coroutines.launch

class PopularViewModel : ViewModel() {
    private val repository = PopularRepository()

    private val result = MutableLiveData<AudioClips>()
    fun getPopular(): LiveData<AudioClips> = result

    private val snackbarMsg = MutableLiveData<Int>()
    fun getSnackbarMsg() = snackbarMsg

    suspend fun getPopularViewModel() {
        viewModelScope.launch {
            try {
                val resultService = repository.getPopular()
                result.value = resultService
            } catch (e: Exception) {
                Log.e("", "result del error del servicio---> ${e.message}")
                snackbarMsg.value = R.string.main_error_server
            }
        }
    }
}