package com.example.audioboom.mainModule.view.fragments

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.audioboom.databinding.FragmentPlayHighBinding


class PlayHighFragment : Fragment() {

    private lateinit var binding: FragmentPlayHighBinding
    private lateinit var runnable: Runnable
    private lateinit var handler: Handler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayHighBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
    }

    private fun sutupPlayer(song: String) {
        val mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer.setDataSource(song)
        binding.seekBar.progress = 0
        binding.seekBar.max = mediaPlayer.duration

        binding.playMedia.setOnClickListener {
            if (!mediaPlayer.isPlaying)
                mediaPlayer.start()
            else
                mediaPlayer.stop()

        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if (changed)
                    mediaPlayer.seekTo(pos)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        runnable = Runnable {
            binding.seekBar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        mediaPlayer.setOnCompletionListener {
            binding.seekBar.progress = 0
        }
    }


    private fun getData() {
        val song = arguments?.getString("song") ?: ""
        val title = arguments?.getString("title") ?: ""
        val image = arguments?.getString("image") ?: ""
        sutupPlayer(song)
        Glide.with(this)
            .load(image)
            .into(binding.photoPlay)
    }


}