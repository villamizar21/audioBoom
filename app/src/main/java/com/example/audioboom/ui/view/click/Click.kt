package com.example.audioboom.ui.view.click

interface Click {
    fun clicked(value: Long?)
}
interface ClickChannel{
    fun clicked(value: String, title: String, description: String,image: String)
}