package com.dagger.hilt.coroutines

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * A custom wrapper of the [kotlinx.coroutines.Dispatchers].
 */
object CoroutineDispatcherWrapper {

    private var default: CoroutineDispatcher = Dispatchers.Default
    private var main: CoroutineDispatcher = Dispatchers.Main
    private var unconfined: CoroutineDispatcher = Dispatchers.Unconfined
    private var io: CoroutineDispatcher = Dispatchers.IO

    val Default: CoroutineDispatcher
        get() = default
    val Main: CoroutineDispatcher
        get() = main
    val Unconfined: CoroutineDispatcher
        get() = unconfined
    val IO: CoroutineDispatcher
        get() = io

    @VisibleForTesting
    fun setDefault(default: CoroutineDispatcher) {
        this.default = default
    }

    @VisibleForTesting
    fun setMain(main: CoroutineDispatcher) {
        this.main = main
    }

    @VisibleForTesting
    fun setUnconfined(unconfined: CoroutineDispatcher) {
        this.unconfined = unconfined
    }

    @VisibleForTesting
    fun setIO(io: CoroutineDispatcher) {
        this.io = io
    }
}
