package com.app.koltinpoc.api

import com.app.koltinpoc.model.NewResponse
import com.app.koltinpoc.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TopHeadlinesApi {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
    ): Response<NewResponse>

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("pageSize")
        pageSize: Int = 10,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewResponse>

    @GET("v2/top-headlines")
    suspend fun getCategory(
        @Query("apiKey") key: String,
        @Query("language") language: String,
        @Query("category") category: String,
    ): Response<NewResponse>
}