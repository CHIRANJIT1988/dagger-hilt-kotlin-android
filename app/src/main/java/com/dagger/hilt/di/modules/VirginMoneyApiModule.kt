package com.dagger.hilt.di.modules

import com.dagger.hilt.BuildConfig
import com.dagger.hilt.data.api.HttpLogger
import com.dagger.hilt.data.api.VirginMoneyApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module(includes = [VirginMoneyApiBindingModule::class])
@InstallIn(SingletonComponent::class)
class VirginMoneyApiModule {

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String = "https://61e947967bc0550017bc61bf.mockapi.io"

    @Provides
    fun provideHttpLoggingInterceptor(logger: HttpLoggingInterceptor.Logger): HttpLoggingInterceptor {
        val logInterceptor = HttpLoggingInterceptor(logger)
        if (BuildConfig.DEBUG) {
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return logInterceptor
    }

    @Provides
    fun provideOkHttpClient(logInterceptor: HttpLoggingInterceptor) : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logInterceptor)
        .build()

    @Provides
    fun provideRetrofitBuilder(@Named("baseUrl") baseUrl: String, mOkHttpClient: OkHttpClient) : Retrofit.Builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)

    @Provides
    fun provideRetrofitClient(builder: Retrofit.Builder) : Retrofit = builder.build()

    @Provides
    fun provideVirginMoneyApi(retrofit: Retrofit) : VirginMoneyApi = retrofit.create(VirginMoneyApi::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class VirginMoneyApiBindingModule {

    @Binds
    abstract fun bindHttpLogger(httpLogger: HttpLogger): HttpLoggingInterceptor.Logger
}
