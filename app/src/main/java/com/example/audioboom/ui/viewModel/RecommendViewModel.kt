package com.example.audioboom.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioboom.R
import com.example.audioboom.data.repository.recomendados.RecommendedRepository
import com.example.audioboom.data.models.recommended.Recommended
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(private val repository: RecommendedRepository): ViewModel() {

    private val result = MutableLiveData<Recommended>()
    fun getRecommended(): LiveData<Recommended> = result

    private val snackbarMsg = MutableLiveData<Int>()
    fun getSnackbarMsg() = snackbarMsg

    suspend fun getRecommendedViewModel() {
        viewModelScope.launch {
            try {
                val resultService = repository.getRecommended()
                result.value = resultService
            } catch (e: Exception) {
                Log.e("", "result del error del servicio---> ${e.message}")
                snackbarMsg.value = R.string.main_error_server
            }
        }
    }
}