package com.example.audioboom.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioboom.R
import com.example.audioboom.data.repository.popular.PopularRepository
import com.example.audioboom.data.models.channels.AudioClips
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(private val repository: PopularRepository) : ViewModel() {

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