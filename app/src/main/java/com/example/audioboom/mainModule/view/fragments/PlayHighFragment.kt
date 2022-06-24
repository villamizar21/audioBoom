package com.example.audioboom.mainModule.view.fragments

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
import com.google.android.material.snackbar.Snackbar


class PlayHighFragment : Fragment() {

    private lateinit var binding: FragmentPlayHighBinding
    private lateinit var handler: Handler
    private var mediaPlayer: MediaPlayer? = null

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

    private fun getData() {
        val song = arguments?.getString("song") ?: ""
        val title = arguments?.getString("title") ?: ""
        val image = arguments?.getString("image") ?: ""
        sutupPlayer(song)
        Glide.with(this)
            .load(image)
            .into(binding.photoPlay)
        binding.title.text = title
    }

    private fun sutupPlayer(song: String) {

        binding.playMedia.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(context, song.toUri())
                initSeekbar()
            }

            mediaPlayer?.start()
            Snackbar.make(binding.root, "Reproduciendo", Snackbar.LENGTH_LONG).show()
        }

        binding.pause.setOnClickListener {
            if (mediaPlayer !== null) mediaPlayer?.pause()

            Snackbar.make(binding.root, "Pausando", Snackbar.LENGTH_LONG).show()
        }

        binding.stop.setOnClickListener {
            if (mediaPlayer !== null) {
                mediaPlayer?.stop()
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
                Snackbar.make(binding.root, "Deteniendo", Snackbar.LENGTH_LONG).show()
            }
        }


        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if (changed)
                    mediaPlayer?.seekTo(pos)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

    }

    private fun initSeekbar() {
        binding.seekBar.max = mediaPlayer!!.duration

        handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    binding.seekBar.progress = mediaPlayer!!.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (e: Exception) {
                    binding.seekBar.progress = 0
                }
            }

        }, 0)
    }
}