package com.example.audioboom.mainModule.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.audioboom.R
import com.example.audioboom.databinding.FragmentChannelsBinding
import com.example.audioboom.mainModule.view.adapter.AdapterChannels
import com.example.audioboom.mainModule.view.adapter.AdapterPopular
import com.example.audioboom.mainModule.view.click.Click
import com.example.audioboom.mainModule.viewModel.ChannelViewModel
import com.example.audioboom.mainModule.viewModel.PopularViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class ChannelsFragment : Fragment(), Click {

    private lateinit var binding: FragmentChannelsBinding
    private val viewModel = ChannelViewModel()
    private val viewModelPopular = PopularViewModel()
    private lateinit var adapterChannel: AdapterChannels
    private lateinit var adapterPopular: AdapterPopular
    private lateinit var fragment: Fragment


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChannelsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        getPopular()
        setupObserver()
        setupRecylcerView()
        setupRecylcerpopular()
    }

    private fun setupRecylcerpopular() {
       adapterPopular = AdapterPopular(this)
       binding.recyclerPopular.apply {
           setHasFixedSize(true)
           layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
           adapter = adapterPopular
       }
    }

    private fun setupRecylcerView() {
        adapterChannel = AdapterChannels(this)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter = adapterChannel
        }
    }

    private fun setupObserver() {
        viewModel.let {
            it.getChannels().observe(viewLifecycleOwner) { result ->
                adapterChannel.submitList(result.body.audio_clips)
            }
            it.getSnackbarMsg().observe(viewLifecycleOwner) { msg ->
                Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG).show()
            }
        }
        viewModelPopular.let {
            it.getPopular().observe(viewLifecycleOwner) { popular ->
                adapterPopular.submitList(popular.body.audio_clips)
                Log.e("","result 6 ${popular.body.audio_clips}")
            }
        }
    }

    private fun getData() {
        lifecycleScope.launch {
            viewModel.getChannelViewModel()
        }
    }

    private fun getPopular() {
        lifecycleScope.launch {
            viewModelPopular.getPopularViewModel()
        }
    }

    override fun clicked(value: Long?) {
        val id = Bundle()
        id.putString("id", value.toString() ?: "")
        fragment = InfoChannelFragment()
        fragment.arguments = id
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container_fragment, fragment)
            addToBackStack(null)
            commit()
        }
    }
}