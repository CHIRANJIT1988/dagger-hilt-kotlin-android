package com.dagger.hilt.data.api

import com.dagger.hilt.data.models.People
import retrofit2.Response
import retrofit2.http.GET

interface VirginMoneyApi {

    @GET("/api/v1/people")
    suspend fun getPeoples() : Response<List<People>>?
}
