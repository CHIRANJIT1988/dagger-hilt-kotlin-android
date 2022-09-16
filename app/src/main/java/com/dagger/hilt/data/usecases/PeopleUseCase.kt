package com.dagger.hilt.data.usecases

import com.dagger.hilt.coroutines.CoroutineDispatcherWrapper
import com.dagger.hilt.data.api.VirginMoneyApi
import com.dagger.hilt.data.models.People
import com.dagger.hilt.data.states.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.distinctUntilChanged
import java.lang.Exception
import javax.inject.Inject

class PeopleUseCase @Inject constructor(private val virginMoneyApi: VirginMoneyApi)
    : BaseUseCase<Unit, Flow<Result<List<People>>>>(CoroutineDispatcherWrapper.IO){

    override suspend fun execute(vararg params: Unit): Flow<Result<List<People>>> = flow<Result<List<People>>> {
        emit(Result.loading())

        val result: Result<List<People>> = try {
            virginMoneyApi.getPeoples().let {
                if(it!!.isSuccessful) {
                    Result.success(it.body())
                } else {
                    Result.none()
                }
            }
        } catch (e: Exception) {
            Result.error<List<People>>(e)
        }

        emit(result)
    }.distinctUntilChanged()
}
