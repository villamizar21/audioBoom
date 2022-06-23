package com.example.audioboom.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.audioboom.R
import com.example.audioboom.mainModule.view.fragments.ChannelsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container_fragment, ChannelsFragment())
            commit()
        }
    }
}