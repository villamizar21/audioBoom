package com.example.audioboom.mainModule.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.audioboom.databinding.FragmentInfoChannelBinding
import com.example.audioboom.mainModule.view.adapter.AdapterInfoChannel
import com.example.audioboom.mainModule.view.click.Click
import com.example.audioboom.mainModule.viewModel.InfoChannelViewModel
import com.example.audioboom.mainModule.viewModel.PlayListViewModel
import com.example.audioboom.utils.Constans
import kotlinx.coroutines.launch


class InfoChannelFragment : Fragment(), Click {

    private lateinit var binding: FragmentInfoChannelBinding
    private val viewModel = InfoChannelViewModel()
    private val viewModelPlayList = PlayListViewModel()
    var id = ""
    private lateinit var adapterPlayList: AdapterInfoChannel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInfoChannelBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        setupRecylcerView()
    }

    private fun setupRecylcerView() {
       adapterPlayList = AdapterInfoChannel(this)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context,2)
            adapter = adapterPlayList
        }
    }

    private fun getData() {
        val image = binding.photo
        id = arguments?.getString(Constans.ID) ?: ""
        Log.e("", "result del id---->$id")

        lifecycleScope.launch {
            viewModel.getInfChannelViewModel(id)
        }

        viewModel.let {
            it.getInfChannel().observe(viewLifecycleOwner) { details ->
                binding.title.text = details.body.channel.title
                binding.description.text = details.body.channel.description
                Glide.with(this).load(details.body.channel.urls.logo_image.original).into(image)
            }
        }

        lifecycleScope.launch {
            viewModelPlayList.getPlayListViewModel(id)
        }

        viewModelPlayList.let {
            it.getPlayList().observe(viewLifecycleOwner){playList ->
                adapterPlayList.submitList(playList.body.audio_clips)
                Log.e("","que fue lo que paso ${playList}")
            }
        }
    }

    override fun clicked(value: Long?) {
    }
}