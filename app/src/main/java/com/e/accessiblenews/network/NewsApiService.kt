package com.e.accessiblenews.network

import android.app.Service
import com.e.accessiblenews.BuildConfig
import com.e.accessiblenews.R
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

private const val BASE_URL = "https://api.nytimes.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface NewsApiService {
    @GET("svc/topstories/v2/home.json")
    fun getNews(@Query("api-key") apiKey: String): Call<String>
}

// Expose the service I made above to the rest of the app
object NewsAPI {
    val retrofitService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}