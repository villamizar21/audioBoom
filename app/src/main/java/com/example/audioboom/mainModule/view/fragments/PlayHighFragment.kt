package com.example.audioboom.mainModule.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.audioboom.databinding.FragmentPlayHighBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerControlView


class PlayHighFragment : Fragment(), Player.Listener {

    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerControlView

    private lateinit var binding: FragmentPlayHighBinding
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
        sutupPlayer()
        getData()
    }

    private fun sutupPlayer() {
        player = ExoPlayer.Builder(requireContext()).build()
        playerView = binding.player
        playerView.player = player
        player.addListener(this)
    }

    private fun getData() {
        val song = arguments?.getString("song") ?: ""
        val title = arguments?.getString("title") ?: ""
        val image = arguments?.getString("image") ?: ""
        val item: MediaItem = MediaItem.fromUri(song)
        initPlayList(item)
        Glide.with(this)
            .load(image)
            .into(binding.photoPlay)
    }

    private fun initPlayList(item: MediaItem) {
        player.addMediaItem(item)
        player.prepare()

    }
}