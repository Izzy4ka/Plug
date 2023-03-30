package com.example.plug.ui.activity.plug.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plug.databinding.ItemNewsBinding
import com.example.plug.entity.news.entity.NewsEntity

class NewsAdapter(private val list: List<NewsEntity>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun onBind(position: Int) {
            Glide.with(binding.itemImage).load(list[position].image).into(binding.itemImage)
            binding.itemNewsTitle.text = list[position].title
            binding.itemNewsDescription.text = list[position].description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = list.size
}