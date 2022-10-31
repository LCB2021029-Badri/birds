package com.example.get_set_go.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.get_set_go.databinding.StyleOvalListItemBinding
import com.example.get_set_go.model.Style

class StyleOvalAdapter(private val onItemClicked: (Style) -> Unit) :
    ListAdapter<Style, StyleOvalAdapter.StyleOvalViewHolder>(DiffCallback) {

    private lateinit var context: Context

    class StyleOvalViewHolder(private var binding: StyleOvalListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(style: Style, context: Context) {
            binding.title.text = context.getString(style.titleResourceId)
            binding.subTitle.text = context.getString(style.subTitleResourceId)
            // Load the images into the ImageView using the Coil library.
            binding.sportsImage.load(style.imageResourceId) // from "list item.xml"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StyleOvalViewHolder {
        context = parent.context
        return StyleOvalViewHolder(
            StyleOvalListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: StyleOvalViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current, context)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Style>() {
            override fun areItemsTheSame(oldItem: Style, newItem: Style): Boolean {
                return (oldItem.id == newItem.id ||
                        oldItem.titleResourceId == newItem.titleResourceId ||
                        oldItem.subTitleResourceId == newItem.subTitleResourceId
                        )
            }

            override fun areContentsTheSame(oldItem: Style, newItem: Style): Boolean {
                return oldItem == newItem
            }
        }
    }
}