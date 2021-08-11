package com.e.accessiblenews.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.accessiblenews.BuildConfig
import com.e.accessiblenews.model.Article
import com.e.accessiblenews.network.NewsAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

private const val API_KEY = BuildConfig.NYT_API_KEY
enum class NewsApiStatus {LOADING, ERROR, DONE}

class OverviewViewModel: ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // LiveData Properties
    private val _status = MutableLiveData<NewsApiStatus>()
    val status: LiveData<NewsApiStatus>
        get() = _status

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    private val _navigateToSelectedArticle = MutableLiveData<Article>()
    val navigateToSelectedArticle: LiveData<Article>
        get() = _navigateToSelectedArticle

    init {
        getNewsData()
    }

    private fun getNewsData() {
        coroutineScope.launch {
            val getNewsDeferred = NewsAPI.retrofitService.getNewsAsync(API_KEY)

            try {
                _status.value = NewsApiStatus.LOADING
                val news = getNewsDeferred.await()
                _status.value = NewsApiStatus.DONE
                _articles.value = news.results
                Log.i("OverViewVM", "✅️ Request Successful: ${news.status}")
            } catch (e: Exception) {
                _status.value = NewsApiStatus.ERROR
                _articles.value = ArrayList()
                Log.i("OverViewVM", "⛔️ Request Failed: ${e.message} - ${e.cause}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun showDetailsOf(article: Article) {
        _navigateToSelectedArticle.value = article
    }

    fun doneNavigatingToDetails() {
        _navigateToSelectedArticle.value = null
    }
}
