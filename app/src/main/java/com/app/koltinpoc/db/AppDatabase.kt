package com.app.koltinpoc.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.koltinpoc.db.dao.ArticleDao
import com.app.koltinpoc.model.Article

@Database(
    version = 1,
    entities = [Article::class],
)
@TypeConverters(Converter::class)
abstract class AppDatabase :RoomDatabase(){

    abstract fun articleDao(): ArticleDao
}