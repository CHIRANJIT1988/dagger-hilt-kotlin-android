package com.dagger.hilt.data.usecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class BaseUseCase<PARAM, RESULT> constructor(
    protected val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(vararg params: PARAM): RESULT {
        return withContext(coroutineDispatcher) {
            execute(*params)
        }
    }

    protected abstract suspend fun execute(vararg params: PARAM): RESULT
}
