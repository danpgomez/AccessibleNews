package com.e.accessiblenews

import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.e.accessiblenews.model.Article
import com.e.accessiblenews.overview.NewsApiStatus
import com.e.accessiblenews.overview.NewsListAdapter
import java.time.format.DateTimeFormatter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Article>?) {
    val adapter = recyclerView.adapter as NewsListAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        GlideApp.with(imgView.context)
            .load(imgUri)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(imgView)
    }
}

@BindingAdapter("newsApiStatus")
fun bindStatus(statusImageView: ImageView, status: NewsApiStatus?) {
    when (status) {
        NewsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        NewsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        NewsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@BindingAdapter("publishedDate")
fun bindPublishedDate(dateTextView: TextView, value: String) {
    var dateFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val parsedDate = dateFormatter.parse(value)
    dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy")
    val formattedDate = dateFormatter.format(parsedDate)
    dateTextView.text = formattedDate
}
