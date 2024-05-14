package com.ee.appcentnews.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ee.appcentnews.model.News

@Database(entities = [News::class], version = 1)
abstract class ArticlesDatabase: RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao

    //It does not create more than one database at the same time by separating it from the main thread
    companion object{
        @Volatile private var instance : ArticlesDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ArticlesDatabase::class.java,
            "FavoriteArticlesTable"
        ).build()
    }
}