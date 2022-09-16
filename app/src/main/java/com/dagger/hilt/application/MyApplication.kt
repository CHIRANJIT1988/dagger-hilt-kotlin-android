package com.dagger.hilt.application

import android.app.Application
import com.dagger.hilt.coroutines.CoroutineDispatcherWrapper
import com.dagger.hilt.ui.utils.ImageUtils
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@HiltAndroidApp
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        GlobalScope.launch(CoroutineDispatcherWrapper.IO) {
            initImageUtils()
        }
    }

    private fun initImageUtils() {
        ImageUtils.init(this) // Important! we need this Singleton to the Application
    }
}
