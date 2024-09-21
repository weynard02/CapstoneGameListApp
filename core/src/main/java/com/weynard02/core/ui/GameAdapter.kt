package com.weynard02.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.weynard02.core.databinding.ItemListGameBinding
import com.weynard02.core.domain.model.Game

class GameAdapter : ListAdapter<Game, GameAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Game) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(private val binding: ItemListGameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Game) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.backgroundImage)
                    .into(ivItemImage)
                tvItemTitle.text = data.name
                root.setOnClickListener {
                    onItemClick?.invoke(data)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Game>() {
            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem == newItem
            }
        }
    }
}