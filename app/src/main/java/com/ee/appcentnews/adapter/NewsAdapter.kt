package com.ee.appcentnews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ee.appcentnews.databinding.RecyclerRowBinding
import com.ee.appcentnews.model.News
import com.ee.appcentnews.utils.extensions.OnNewsItemClickListener


class NewsAdapter(val newsList: ArrayList<News>,private val setOnNewsItemClickListener: OnNewsItemClickListener): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(private val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: News) {
            binding.titleTv.text = item.title
            binding.introTv.text = item.description
            Glide.with(itemView).load(item.urlToImage).centerCrop().into(binding.imageViewPlace)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsList[position]
        holder.bind(newsItem)
        holder.itemView.setOnClickListener {
            setOnNewsItemClickListener.onItemClick(newsItem)
        }
    }

    fun updateNewsList(newNewsList: List<News>) {
        newsList.clear()
        newsList.addAll(newNewsList)
        notifyDataSetChanged()
    }


}