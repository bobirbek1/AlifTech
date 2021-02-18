package com.idrok.aliftech.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {

    @BindingAdapter("setImage")
    @JvmStatic
    fun setImage(view: ImageView, url: String) {
        Glide.with(view)
            .load(url)
            .into(view)
    }

}