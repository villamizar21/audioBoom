package com.example.audioboom.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.audioboom.R
import com.example.audioboom.databinding.ItemPlayListBinding
import com.example.audioboom.data.models.infoChannel.channelSeleccted.AudioClipsSelected
import com.example.audioboom.ui.view.click.ClickChannel
import com.google.android.material.snackbar.Snackbar

class AdapterInfoChannel(private val listener: ClickChannel) :
    ListAdapter<AudioClipsSelected, RecyclerView.ViewHolder>(ChannelDiffCallback()) {

    private lateinit var context: Context
    private lateinit var recyclerView: RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_play_list, parent, false)
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val channel = getItem(position)
        with(holder as ViewHolder) {
            Glide.with(holder.view)
                .load(channel.channel.urls.logo_image.original)
                .into(binding.channelPoster)
            binding.title.text = channel.title
            binding.channelPoster.setOnClickListener {

                try{
                    if(channel.urls.high_mp3.isNotEmpty() &&
                        channel.title.isNotEmpty() && channel.description.isNotEmpty() &&
                        channel.channel.urls.logo_image.original.isNotEmpty())
                        listener.clicked(channel.urls.high_mp3, channel.title, channel.description,channel.channel.urls.logo_image.original)
                }catch (e: Exception){
                    Snackbar.make(binding.root, "Error ${e.message}", Snackbar.LENGTH_LONG).show()
                }
            }
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
        override fun areItemsTheSame(
            oldItem: AudioClipsSelected,
            newItem: AudioClipsSelected
        ): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: AudioClipsSelected,
            newItem: AudioClipsSelected
        ): Boolean =
            oldItem == newItem

    }
}