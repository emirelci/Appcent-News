package com.ee.appcentnews.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ee.appcentnews.model.Articles
import com.ee.appcentnews.model.News
import com.ee.appcentnews.service.NewsAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class NewsViewModel(application: Application) : BaseViewModel(application) {

    private val newsApIService = NewsAPIService()
    private val disposable = CompositeDisposable()

    val newsList = MutableLiveData<List<News>>()

    //Get 'Türkiye' Data sample query from API
    fun getDataFromAPI() {
        disposable.add(
            newsApIService.getData().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Articles>() {
                    override fun onSuccess(t: Articles) {
                        newsList.value = t.articles
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }

    //Get Data sample query by search from API
    fun getQueryDataFromAPI(query: String) {
        disposable.add(
            newsApIService.getQueryNews(query).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Articles>() {
                    override fun onSuccess(t: Articles) {
                        newsList.value = t.articles
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }

}