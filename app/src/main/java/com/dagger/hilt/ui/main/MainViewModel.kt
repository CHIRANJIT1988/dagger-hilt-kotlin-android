package com.dagger.hilt.ui.main

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dagger.hilt.coroutines.CoroutineDispatcherWrapper
import com.dagger.hilt.data.models.People
import com.dagger.hilt.data.states.Result
import com.dagger.hilt.data.usecases.PeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val peopleUseCase: PeopleUseCase
): ViewModel() {

    @Inject
    lateinit var people: MutableLiveData<People>
    @Inject
    lateinit var peoples: MutableLiveData<Result<List<People>>>
    @Inject
    lateinit var loadingVisibility: MutableLiveData<Int>
    @Inject
    lateinit var error: MutableLiveData<Boolean>

    fun getPeoples() {
        viewModelScope.launch(CoroutineDispatcherWrapper.IO) {
            peopleUseCase().collect { result ->
                when {
                    result.isLoading() -> setLoadingVisibility(true)
                    result.isSuccess() -> {
                        setLoadingVisibility(false)
                        peoples.postValue(result)
                    }
                    result.isError() -> {
                        setLoadingVisibility(false)
                        error.postValue(true)
                    }
                    result.isNone() -> {
                        setLoadingVisibility(false)
                        error.postValue(true)
                    }
                }
            }
        }
    }

    fun setLoadingVisibility(visible: Boolean) {
        loadingVisibility.postValue(if (visible) View.VISIBLE else View.GONE)
    }
}

