package com.example.audioboom.mainModule.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.audioboom.R
import com.example.audioboom.databinding.FragmentInfoChannelBinding
import com.example.audioboom.mainModule.view.adapter.AdapterInfoChannel
import com.example.audioboom.mainModule.view.click.ClickChannel
import com.example.audioboom.mainModule.viewModel.InfoChannelViewModel
import com.example.audioboom.mainModule.viewModel.PlayListViewModel
import com.example.audioboom.utils.Constans
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class InfoChannelFragment : Fragment(), ClickChannel {

    private lateinit var binding: FragmentInfoChannelBinding
    private val viewModel = InfoChannelViewModel()
    private val viewModelPlayList = PlayListViewModel()
    var id = ""
    private lateinit var adapterPlayList: AdapterInfoChannel
    private lateinit var fragment: Fragment

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
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
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
            it.getSnackbarMsg().observe(viewLifecycleOwner) {msg ->
                Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG).show()
            }
            it.isLoaded().observe(viewLifecycleOwner) { status ->
                if (status)
                    binding.progressBar.visibility = View.GONE
            }
        }

        lifecycleScope.launch {
            viewModelPlayList.getPlayListViewModel(id)
        }

        viewModelPlayList.let {
            it.getPlayList().observe(viewLifecycleOwner) { playList ->
                adapterPlayList.submitList(playList.body.audio_clips)
                Log.e("", "que fue lo que paso ${playList}")
            }
            it.getSnackbarMsg().observe(viewLifecycleOwner) {msg ->
                Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun clicked(value: String, title: String, description: String, image: String) {
        val song = Bundle()
        song.putString("song", value)
        song.putString("title", title)
        song.putString("description", description)
        song.putString("image", image)
        fragment = PlayHighFragment()
        fragment.arguments = song
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container_fragment, fragment)
            addToBackStack(null)
            commit()
        }
    }
}