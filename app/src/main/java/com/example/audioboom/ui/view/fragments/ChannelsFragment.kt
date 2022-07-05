package com.example.audioboom.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.audioboom.R
import com.example.audioboom.databinding.FragmentChannelsBinding
import com.example.audioboom.ui.view.adapter.AdapterChannels
import com.example.audioboom.ui.view.adapter.AdapterPopular
import com.example.audioboom.ui.view.adapter.AdapterRecommended
import com.example.audioboom.ui.view.click.Click
import com.example.audioboom.ui.viewModel.ChannelViewModel
import com.example.audioboom.ui.viewModel.PopularViewModel
import com.example.audioboom.ui.viewModel.RecommendViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChannelsFragment : Fragment(), Click {

    private lateinit var binding: FragmentChannelsBinding
    private val viewModel: ChannelViewModel by viewModels()
    private val viewModelPopular: PopularViewModel by viewModels()
    private val viewModelRecommended: RecommendViewModel by viewModels()
    private lateinit var adapterChannel: AdapterChannels
    private lateinit var adapterPopular: AdapterPopular
    private lateinit var adapterRecommended: AdapterRecommended
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
        setupRecylcerpopular()
        setupRecylcerRecommended()
    }

    private fun setupRecylcerRecommended() {
        adapterRecommended = AdapterRecommended(this)
        binding.recyclerRecommend.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterRecommended
        }
    }

    private fun setupRecylcerpopular() {
        adapterPopular = AdapterPopular(this)
        binding.recyclerPopular.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterPopular
        }
    }

    private fun setupRecylcerView() {
        adapterChannel = AdapterChannels(this)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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
            it.isLoaded().observe(viewLifecycleOwner) { status ->
                if (status)
                    binding.progressBar.visibility = View.GONE
            }
        }
        viewModelPopular.let {
            it.getPopular().observe(viewLifecycleOwner) { popular ->
                adapterPopular.submitList(popular.body.audio_clips)
            }
        }
        viewModelRecommended.let {
            it.getRecommended().observe(viewLifecycleOwner) { recommended ->
                adapterRecommended.submitList(recommended.body)
            }
        }
    }

    private fun getData() {
        lifecycleScope.launch {
            viewModel.getChannelViewModel()
            viewModelPopular.getPopularViewModel()
            viewModelRecommended.getRecommendedViewModel()
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