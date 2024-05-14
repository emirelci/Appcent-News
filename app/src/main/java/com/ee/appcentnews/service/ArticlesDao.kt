package com.ee.appcentnews.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ee.appcentnews.model.News

//Data Access Object
@Dao
interface ArticlesDao {

    @Query("SELECT * FROM FavoriteArticlesTable")
    suspend fun getAllArticles(): List<News>

    @Query("DELETE FROM FavoriteArticlesTable WHERE uuid = :newsId")
    suspend fun deleteArticle(newsId: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(articles: News) : Long

}