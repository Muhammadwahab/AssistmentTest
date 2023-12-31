package com.example.assesmenttest

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


object ImageLoaderUtils {

     fun loadImageFromServerCrop(url: String, imgView: ImageView) {

         Glide.with(imgView).load(url).placeholder(R.drawable.image_placeholder_bg).apply(RequestOptions()).diskCacheStrategy(
             DiskCacheStrategy.ALL).into(imgView)

    }


}