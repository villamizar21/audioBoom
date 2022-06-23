package com.example.audioboom.mainModule.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.audioboom.R
import com.example.audioboom.databinding.FragmentChannelsBinding
import com.example.audioboom.mainModule.view.adapter.AdapterChannels
import com.example.audioboom.mainModule.view.click.Click
import com.example.audioboom.mainModule.viewModel.ChannelViewModel
import kotlinx.coroutines.launch


class ChannelsFragment : Fragment(), Click {

    private lateinit var binding: FragmentChannelsBinding
    private val viewModel = ChannelViewModel()
    private lateinit var adapterChannel: AdapterChannels
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
        setupObserver()
        setupRecylcerView()
    }

    private fun setupRecylcerView() {
        adapterChannel = AdapterChannels(this)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = adapterChannel
        }
    }

    private fun setupObserver() {
        viewModel.let {
            it.getChannels().observe(viewLifecycleOwner) { result ->
                adapterChannel.submitList(result.body.audio_clips)
            }
        }
    }

    private fun getData() {
        lifecycleScope.launch {
            viewModel.getChannelViewModel()
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