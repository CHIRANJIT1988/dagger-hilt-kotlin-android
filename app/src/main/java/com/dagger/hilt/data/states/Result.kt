package com.dagger.hilt.data.states

/**
 * A generic class that contains data and status/states about loading this data.
 */
sealed class Result<T>(
    val data: T? = null,
    val message: String? = null,
    val error: Exception? = null
) {
    /**
     * This class represents the successful status/state of the data being loaded.
     *
     * @param data data being loaded.
     */
    class Success<T>(data: T?) : Result<T>(data)

    /**
     * This class represents the loading status/state of the data being loaded.
     *
     * @param data data being loaded.
     */
    class Loading<T>(data: T? = null) : Result<T>(data)

    /**
     * This class represents the error status/state of the data being loaded.
     *
     * @param exception the error.
     * @param data data being loaded.
     */
    class Error<T>(exception: Exception, data: T? = null) : Result<T>(
        data = data,
        message = "",
        error = exception
    )

    /**
     * This is used to specify no state or unspecified state.
     * UI should not do anything in response to this.
     */
    class None<T> : Result<T>()

    /**
     * @return TRUE if the instance is of [Loading].
     */
    fun isLoading() = this is Loading

    /**
     * @return TRUE if the instance is of [Success].
     */
    fun isSuccess() = this is Success

    /**
     * @return TRUE if the instance is of [Error].
     */
    fun isError() = this is Error

    /**
     * @return TRUE if the instance is of [None].
     */
    fun isNone() = this is None

    companion object {
        /**
         * Create a successful status with data.
         *
         * @param data data successfully loaded.
         */
        fun <T> success(data: T? = null): Result<T> {
            return Success(data)
        }

        /**
         * Create an error status with optional data.
         *
         * @param error the error.
         * @param data optional data.
         */
        fun <T> error(exception: Exception, data: T? = null): Result<T> {
            return Error(exception, data)
        }

        /**
         * Create a loading status with the optional data.
         *
         * @param data optional data.
         */
        fun <T> loading(data: T? = null): Result<T> {
            return Loading(data)
        }

        /**
         * Create a None result type.
         */
        fun <T> none(): Result<T> {
            return None()
        }
    }
}
