package com.example.get_set_go.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.get_set_go.databinding.AboutusListItemBinding
import com.example.get_set_go.databinding.HometopListItemBinding
import com.example.get_set_go.model.Sport

class HometopAdapter(private val onItemClicked: (Sport) -> Unit) :
    ListAdapter<Sport, HometopAdapter.HometopHolder>(DiffCallback) {

    private lateinit var context: Context

    class HometopHolder(private var binding: HometopListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sport: Sport, context: Context) {
            binding.title.text = context.getString(sport.titleResourceId)
//            binding.subTitle.text = context.getString(sport.subTitleResourceId)
            // Load the images into the ImageView using the Coil library.
            binding.sportsImage.load(sport.imageResourceId)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HometopHolder {
        context = parent.context
        return HometopHolder(
            HometopListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HometopHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current, context)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Sport>() {
            override fun areItemsTheSame(oldItem: Sport, newItem: Sport): Boolean {
                return (oldItem.id == newItem.id ||
                        oldItem.titleResourceId == newItem.titleResourceId ||
                        oldItem.subTitleResourceId == newItem.subTitleResourceId
                        )
            }

            override fun areContentsTheSame(oldItem: Sport, newItem: Sport): Boolean {
                return oldItem == newItem
            }
        }
    }
}