package com.weynard02.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.weynard02.core.R
import com.weynard02.core.databinding.ItemListGameBinding
import com.weynard02.core.domain.model.Game
import java.util.ArrayList

class GameAdapter : RecyclerView.Adapter<GameAdapter.ListViewHolder>() {
    private var listData = ArrayList<Game>()
    var onItemClick: ((Game) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_game, parent, false))


    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Game>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }



    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListGameBinding.bind(itemView)
        fun bind(data: Game) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.backgroundImage)
                    .into(ivItemImage)
                tvItemTitle.text = data.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }
}