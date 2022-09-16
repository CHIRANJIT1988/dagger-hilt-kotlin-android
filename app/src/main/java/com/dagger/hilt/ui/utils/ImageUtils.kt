package com.dagger.hilt.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.BuildConfig
import com.squareup.picasso.Picasso

object ImageUtils {

    @SuppressLint("StaticFieldLeak") // ignore the warning as we use the Application Context
    lateinit var picasso: Picasso

    fun init(appContext: Context) {
        picasso = Picasso.Builder(appContext)
            .loggingEnabled(BuildConfig.DEBUG)
            .build()
    }

    fun loadFromUrl(imgUrl: String?, imgView: ImageView) {
        picasso.load(imgUrl).into(imgView)
    }
}
