package com.e.accessiblenews.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.accessiblenews.BuildConfig
import com.e.accessiblenews.network.NewsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val API_KEY = BuildConfig.NYT_API_KEY

class OverviewViewModel: ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getNewsData()
    }

    private fun getNewsData() {
        NewsAPI.retrofitService.getNews(API_KEY).enqueue(object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
            }

        })
    }
}