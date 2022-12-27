package com.app.koltinpoc.viewModel

import androidx.core.app.NotificationCompat.getCategory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.koltinpoc.di.DBRepository
import com.app.koltinpoc.di.NetworkRepository
import com.app.koltinpoc.model.NewResponse
import com.app.koltinpoc.utils.Constants
import com.app.koltinpoc.utils.Constants.API_KEY
import com.app.koltinpoc.utils.Constants.COUNTRY_CODE
import com.app.koltinpoc.utils.Constants.GENERAL
import com.app.koltinpoc.utils.Constants.LANGUAGE
import com.app.koltinpoc.utils.Constants.SPORT
import com.app.koltinpoc.utils.Constants.TECH
import com.app.koltinpoc.utils.DataHandler
import com.bumptech.glide.load.engine.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
 class OnlineViewModel @Inject constructor(private val networkRepository: NetworkRepository) : ViewModel() {

    private val _topHeadlines = MutableLiveData<DataHandler<NewResponse>>()
    val topHeadlines: LiveData<DataHandler<NewResponse>> = _topHeadlines
    private val _topCategories = MutableLiveData<DataHandler<NewResponse>>()
    val topCategories: LiveData<DataHandler<NewResponse>> = _topCategories
    //val searchNews: MutableLiveData<Resource<NewResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse: NewResponse? = null
    private val _searchNews = MutableLiveData<DataHandler<NewResponse>>()
    val searchNews: LiveData<DataHandler<NewResponse>> = _searchNews


    fun getTopHeadlines() {
        _topHeadlines.postValue(DataHandler.LOADING())
        viewModelScope.launch {
            val response = networkRepository.getTopHeadlines(COUNTRY_CODE, API_KEY)
            _topHeadlines.postValue(handleResponse(response))
        }
    }
    fun getTopCategories(category: String) {
        _topCategories.postValue(DataHandler.LOADING())
        viewModelScope.launch {
            val response = networkRepository.getCategory(API_KEY, LANGUAGE, category)
            _topCategories.postValue(handleResponse(response))
        }
    }
    fun searchNews(searchQuery: String) = viewModelScope.launch {
        _searchNews.postValue(DataHandler.LOADING())
        val response = networkRepository.searchNews(searchQuery, searchNewsPage)
        _searchNews.postValue(handleSearchNewsResponse(response))
    }


    private fun handleSearchNewsResponse(response: Response<NewResponse>): DataHandler<NewResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                searchNewsPage++
                if (searchNewsResponse == null) {
                    searchNewsResponse = resultResponse
                } else {
                    val olArticles = searchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    olArticles?.addAll(newArticles)
                }
                return DataHandler.SUCCESS(searchNewsResponse ?: resultResponse)
            }
        }
        return DataHandler.ERROR(message = response.errorBody().toString())
    }


    private fun handleResponse(response: Response<NewResponse>): DataHandler<NewResponse> {
        if (response.isSuccessful) {
            response.body()?.let { it ->
                return DataHandler.SUCCESS(it)
            }
        }
        return DataHandler.ERROR(message = response.errorBody().toString())
    }


}