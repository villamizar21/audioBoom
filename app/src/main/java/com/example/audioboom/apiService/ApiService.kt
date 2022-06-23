package com.example.audioboom.apiService

import com.example.audioboom.entities.AudioClips
import retrofit2.http.GET

interface ApiService {
    @GET("/audio_clips")
    suspend fun getallaudios():AudioClips
}