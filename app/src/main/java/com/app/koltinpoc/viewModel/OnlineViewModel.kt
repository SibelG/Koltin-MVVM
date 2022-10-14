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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
 class OnlineViewModel @Inject constructor(private val networkRepository: NetworkRepository) : ViewModel() {

    private val query=MutableLiveData<String>("")
    private val _topHeadlines = MutableLiveData<DataHandler<NewResponse>>()
    val topHeadlines: LiveData<DataHandler<NewResponse>> = _topHeadlines
    private val _topCategories = MutableLiveData<DataHandler<NewResponse>>()
    val topCategories: LiveData<DataHandler<NewResponse>> = _topCategories

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

    fun setQuery(s:String){
        query.postValue(s)
    }

    private fun handleResponse(response: Response<NewResponse>): DataHandler<NewResponse> {
        if (response.isSuccessful) {
            response.body()?.let { it ->
                return DataHandler.SUCCESS(it)
            }
        }
        return DataHandler.ERROR(message = response.errorBody().toString())
    }
    /*private suspend fun createRequest(): NewResponse {
        Constants.apply {
            return when (category) {
                ALL -> {
                    networkRepository.getTopHeadlines(COUNTRY_CODE, API_KEY)
                }
                HEALTH -> {
                    networkRepository.getCategory(API_KEY, LANGUAGE, HEALTH)
                }
                TECH -> {
                    networkRepository.getCategory(API_KEY, LANGUAGE, TECH)
                }
                ENTERTAINMENT -> {
                    networkRepository.getCategory(API_KEY, LANGUAGE, ENTERTAINMENT)
                }
                BUSINESS -> {
                    networkRepository.getCategory(API_KEY, LANGUAGE, BUSINESS)
                }
                GENERAL -> {
                    networkRepository.getCategory(API_KEY, LANGUAGE, GENERAL)
                }
                else -> {
                    networkRepository.getCategory(API_KEY, LANGUAGE, SPORT)
                }
            }
        }
    }*/


}