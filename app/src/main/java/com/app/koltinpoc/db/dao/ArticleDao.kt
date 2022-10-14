package com.app.koltinpoc.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.koltinpoc.model.Article
import com.app.koltinpoc.model.NewResponse
import com.app.koltinpoc.utils.DataHandler
import retrofit2.Response

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article):Long

    // NOTE - Since we are already using LIVE-DATA no need to use suspend function
    @Query("SELECT * FROM ARTICLE")
    fun getAllOfflineArticles():LiveData<List<Article>>


    @Delete
    suspend fun delete(article: Article)


    @Query("SELECT * FROM ARTICLE WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<Article>>
}