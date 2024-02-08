package com.dicoding.shof.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.shof.core.R
import com.dicoding.shof.core.databinding.ItemListGamesBinding
import com.dicoding.shof.core.domain.model.Games

class FavoritesAdapter(private val onClickDetails: (Games) -> Unit) :
    ListAdapter<Games, FavoritesAdapter.ListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListGamesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(private val binding: ItemListGamesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Games) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.imageBackground)
                    .into(imgItemGame)
                tvTitle.text = data.name
                tvReleased.text = data.released
                tvRating.text = itemView.context.getString(R.string.rating, data.rating.toString())

                itemView.setOnClickListener {
                    onClickDetails(data)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Games>() {
            override fun areItemsTheSame(oldItem: Games, newItem: Games): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Games, newItem: Games): Boolean {
                return oldItem == newItem
            }
        }
    }
}
