package com.example.coding10

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imgRes")
fun imgRes(imageView: ImageView, uri:Uri) {
    imageView.setImageURI(uri)
}