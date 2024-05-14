package com.ee.appcentnews.service

import com.ee.appcentnews.model.Articles
import com.ee.appcentnews.model.News
import com.ee.appcentnews.utils.extensions.Constants.APIConstants.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class NewsAPIService{

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        .create(NewsAPI::class.java)

    fun getData(): Single<Articles> {
        return api.getNews()
    }

    fun getQueryNews(query:String) : Single<Articles>{
        return api.getQueryNews(query)
    }

}