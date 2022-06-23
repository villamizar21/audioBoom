package com.example.audioboom.mainModule.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.audioboom.R
import com.example.audioboom.databinding.ItemChannelsBinding
import com.example.audioboom.databinding.ItemPlayListBinding
import com.example.audioboom.entities.channels.AudioClips
import com.example.audioboom.entities.infoChannel.channelSeleccted.AudioClipsSelected
import com.example.audioboom.mainModule.view.click.Click

class AdapterInfoChannel(private val listener: Click) :
    ListAdapter<AudioClipsSelected, RecyclerView.ViewHolder>(ChannelDiffCallback())  {

    private lateinit var context: Context
    private lateinit var recyclerView: RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_play_list, parent, false)
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val channel =getItem(position)
        with(holder as ViewHolder){
            Glide.with(holder.view)
                .load(channel.channel.urls.logo_image.original)
                .into(binding.channelPoster)
            binding.title.text = channel.title
            Log.e("","title-----> ${channel.title}")
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPlayListBinding.bind(view)
    }

    class ChannelDiffCallback : DiffUtil.ItemCallback<AudioClipsSelected>() {
        override fun areItemsTheSame(oldItem: AudioClipsSelected, newItem: AudioClipsSelected): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: AudioClipsSelected, newItem: AudioClipsSelected): Boolean =
            oldItem == newItem

    }
}