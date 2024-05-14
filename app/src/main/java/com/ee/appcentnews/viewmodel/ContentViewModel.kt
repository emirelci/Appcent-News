package com.ee.appcentnews.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.ee.appcentnews.R
import com.ee.appcentnews.model.News
import com.ee.appcentnews.service.ArticlesDatabase
import kotlinx.coroutines.launch

class ContentViewModel(application: Application) : BaseViewModel(application) {

    val liked = MutableLiveData<Boolean>()

    //Likes saves the article to the database
    fun storeInSQLite(article: News, context: Context) {
        launch {
            val dao = ArticlesDatabase(getApplication()).articlesDao()
            val favoriteArticle = dao.insert(article)
            if (favoriteArticle == -1L) {
                Toast.makeText(
                    context,
                    context.getString(R.string.wrong_toast_message), Toast.LENGTH_SHORT
                ).show()
            }
        }
        liked.value = true
    }

    //When retrieving articles from the database, it evaluates whether they are liked or not
    fun getFromSQLite(article: News) {
        launch {
            val dao = ArticlesDatabase(getApplication()).articlesDao()
            val favoriteArticle = dao.getAllArticles()

            for (fav in favoriteArticle) {
                if (fav.title == article.title) {
                    liked.value = true
                    break
                } else {
                    liked.value = false
                }
            }
        }
    }

    fun deleteFromSQLite(position: Int) {
        launch {
            val dao = ArticlesDatabase(getApplication()).articlesDao()
            val favoriteArticle = dao.deleteArticle(position)
        }
        liked.value = false
        Log.i("favArticle", liked.value.toString())
    }


}