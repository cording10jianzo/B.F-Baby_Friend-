package com.example.coding10

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imgRes")
fun imgRes(imageView: ImageView, resid:Int) {
    imageView.setImageResource(resid)
}