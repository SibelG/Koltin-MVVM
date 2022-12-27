package com.app.koltinpoc.di

import com.app.koltinpoc.api.TopHeadlinesApi
import com.app.koltinpoc.model.NewResponse
import com.app.koltinpoc.utils.DataHandler
import org.intellij.lang.annotations.Language
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    val topHeadlinesApi: TopHeadlinesApi
) {

    suspend fun getTopHeadlines(country: String, apiKey: String): Response<NewResponse> {
        return topHeadlinesApi.getTopHeadlines(country, apiKey)
    }
    suspend fun getCategory(apiKey: String, language:String, category: String): Response<NewResponse> {
        return topHeadlinesApi.getCategory(apiKey,language, category)
    }
    suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewResponse>  {
        return topHeadlinesApi.searchNews(searchQuery, pageNumber)
    }



}