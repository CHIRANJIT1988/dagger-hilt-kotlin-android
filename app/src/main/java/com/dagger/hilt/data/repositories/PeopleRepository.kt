package com.dagger.hilt.data.repositories

import com.dagger.hilt.coroutines.CoroutineDispatcherWrapper
import com.dagger.hilt.data.api.VirginMoneyApi
import com.dagger.hilt.data.models.People
import com.dagger.hilt.data.states.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PeopleRepository @Inject constructor(private val virginMoneyApi: VirginMoneyApi)
    : BaseRepository(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + CoroutineDispatcherWrapper.IO

    suspend fun getPeoples() : Result<List<People>> {
        val result: Result<List<People>> = try {
            virginMoneyApi.getPeoples().let {
                if(it!!.isSuccessful) Result.success(it.body()) else Result.none()
            }
        } catch (e: Exception) {
            Result.error<List<People>>(e)
        }
        return result
    }
}
