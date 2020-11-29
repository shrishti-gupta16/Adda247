package com.example.codingsample.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.codingsample.data.User
import com.example.codingsample.ui.main.UserListAdapter


@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<User>?) {
    items?.let {
        (listView.adapter as UserListAdapter).submitList(items.toMutableList())
    }
}

@BindingAdapter("app:imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String) {
    imageUrl?.let {
        Glide.with(imageView.context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(RequestOptions.circleCropTransform())
            .into(imageView);
    }
}

@BindingAdapter("app:formatDate")
fun formatDate(textView: TextView, date: String) {
    textView.text = Utils.formatDate(date)
}