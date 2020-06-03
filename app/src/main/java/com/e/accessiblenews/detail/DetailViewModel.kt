package com.e.accessiblenews.detail

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.accessiblenews.model.Article

@RequiresApi(Build.VERSION_CODES.O)
class DetailViewModel(article: Article, application: Application) : ViewModel() {
    private val _selectedArticle = MutableLiveData<Article>()
    val selectedArticle: LiveData<Article>
        get() = _selectedArticle

    init {
        _selectedArticle.value = article
    }


}
