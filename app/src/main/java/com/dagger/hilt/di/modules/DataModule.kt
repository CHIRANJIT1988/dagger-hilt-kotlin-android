package com.dagger.hilt.di.modules

import androidx.lifecycle.MutableLiveData
import com.dagger.hilt.data.models.People
import com.dagger.hilt.data.states.Result
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    @Provides
    fun providePeopleListMutableLiveData() = MutableLiveData<Result<List<People>>>()

    @Provides
    fun providePeopleMutableLiveData() = MutableLiveData<People>()

    @Provides
    fun provideBooleanMutableLiveData() = MutableLiveData<Boolean>()

    @Provides
    fun provideIntegerMutableLiveData() = MutableLiveData<Int>()
}
