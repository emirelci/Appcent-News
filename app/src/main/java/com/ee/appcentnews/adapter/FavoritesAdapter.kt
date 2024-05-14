package com.ee.appcentnews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ee.appcentnews.adapter.FavoritesAdapter.FavoritesViewHolder
import com.ee.appcentnews.databinding.RecyclerRowBinding
import com.ee.appcentnews.model.News
import com.ee.appcentnews.utils.extensions.OnNewsItemClickListener

class FavoritesAdapter(val favoritesList: ArrayList<News>,private val onSetOnNewsItemClickListener: OnNewsItemClickListener) : RecyclerView.Adapter<FavoritesViewHolder>() {

    class FavoritesViewHolder (private val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: News) {
            binding.titleTv.text = item.title
            binding.introTv.text = item.description
            Glide.with(itemView).load(item.urlToImage).centerCrop().into(binding.imageViewPlace)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoritesViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return favoritesList.size

    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val newsItem = favoritesList[position]
        holder.bind(newsItem)
        holder.itemView.setOnClickListener {
            onSetOnNewsItemClickListener.onItemClick(newsItem)
        }
    }

    fun updateNewsList(newNewsList: List<News>) {
        favoritesList.clear()
        favoritesList.addAll(newNewsList)
        notifyDataSetChanged()
    }

}