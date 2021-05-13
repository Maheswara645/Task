package com.example.myapplication.helper

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
// to load the image view and maintain them in constant
class GlideImageLoader(var context: Context) {

    fun load(url: String, imageView: ImageView) {
        val url2 = url.replace("\\/", "/")
        Glide.with(context).load(url2)
            .dontAnimate()
            .apply(requestOptions).into(imageView)
    }

    companion object {
        val requestOptions: RequestOptions
            get() {
                val requestOptions = RequestOptions()
                requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                requestOptions.placeholder(R.drawable.profileplaceholder)
                requestOptions.error(R.drawable.profileplaceholder)
                return requestOptions
            }
    }
}