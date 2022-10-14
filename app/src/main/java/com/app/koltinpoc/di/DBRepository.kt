package com.app.koltinpoc.di

import androidx.lifecycle.LiveData
import com.app.koltinpoc.db.AppDatabase
import com.app.koltinpoc.model.Article
import com.app.koltinpoc.model.NewResponse
import com.app.koltinpoc.utils.DataHandler
import javax.inject.Inject

class DBRepository @Inject constructor(val appDatabase: AppDatabase) {

    suspend fun insertArticle(article: Article): Long {
        return appDatabase.articleDao()
            .insert(article)
    }

    suspend fun delete(article: Article) {
        appDatabase.articleDao().delete(article)
    }

    fun getAllArticles(): LiveData<List<Article>> {
        return appDatabase.articleDao().getAllOfflineArticles()
    }
    fun searchDatabase(searchQuery: String): LiveData<List<Article>> {
        return appDatabase.articleDao().searchDatabase(searchQuery)
    }


}