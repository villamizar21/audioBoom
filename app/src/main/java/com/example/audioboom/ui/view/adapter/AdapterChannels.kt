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
import com.example.audioboom.databinding.ItemChannelsBinding
import com.example.audioboom.data.models.channels.Audios
import com.example.audioboom.ui.view.click.Click

class AdapterChannels(private val listener: Click) :
    ListAdapter<Audios, RecyclerView.ViewHolder>(ChannelDiffCallback()) {

    private lateinit var context: Context
    private lateinit var recyclerView: RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_channels, parent, false)
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val channel =getItem(position)
        with(holder as ViewHolder){
            Glide.with(holder.view)
                .load(channel.channel.urls.logo_image.original)
                .into(binding.channelPoster)
            binding.channelPoster.setOnClickListener {
                listener.clicked(channel.channel.id)
            }
            binding.title.text = channel.title
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemChannelsBinding.bind(view)
    }

    class ChannelDiffCallback : DiffUtil.ItemCallback<Audios>() {
        override fun areItemsTheSame(oldItem: Audios, newItem: Audios): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Audios, newItem: Audios): Boolean =
            oldItem == newItem

    }
}