package com.ee.appcentnews.service

import com.ee.appcentnews.model.Articles
import com.ee.appcentnews.utils.extensions.Constants.APIConstants.TURKEY_NEWS
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET(TURKEY_NEWS)
    fun getNews(): Single<Articles>

    @GET("everything?from=2024-05-05&sortBy=popularity&apiKey=0257400d0def47b88276c02ecaac7999")
    fun getQueryNews(@Query("q") query: String): Single<Articles>
}