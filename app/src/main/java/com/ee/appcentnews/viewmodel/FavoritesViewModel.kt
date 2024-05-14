package com.ee.appcentnews.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ee.appcentnews.model.News
import com.ee.appcentnews.service.ArticlesDatabase
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : BaseViewModel(application) {

    val favoritesList = MutableLiveData<List<News>>()

    fun getInSQLite(){
        launch{
            val dao = ArticlesDatabase(getApplication()).articlesDao()
            favoritesList.value = dao.getAllArticles()
        }
    }
}