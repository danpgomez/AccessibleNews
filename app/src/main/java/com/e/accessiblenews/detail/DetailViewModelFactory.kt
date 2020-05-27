package com.e.accessiblenews.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.e.accessiblenews.model.Article
import java.lang.IllegalArgumentException

class DetailViewModelFactory(private val selectedArticle: Article, private val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(selectedArticle, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}