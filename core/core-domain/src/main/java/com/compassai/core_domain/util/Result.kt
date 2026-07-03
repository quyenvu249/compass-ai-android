package com.compassai.core_domain.util

/**
 * A discriminated union representing the result of an operation that can
 * succeed or fail.
 *
 * Why not throw exceptions?
 * Exceptions are invisible in function signatures — a caller has no idea
 * a function can fail without reading its internals. Result<T> makes the
 * failure case explicit and forces the caller to handle it.
 *
 * Why not use Arrow's Either or Kotlin's built-in Result?
 * - Arrow adds a library dependency to core-domain, which should be lean.
 * - Kotlin's Result is designed for coroutine internals, not domain modeling.
 * - This custom sealed class lets us add domain-specific error types cleanly.
 */
sealed class Result<out T> {

    data class Success<T>(val data: T) : Result<T>()

    data class Error(
        val code: ErrorCode,
        val message: String,
        val cause: Throwable? = null,
    ) : Result<Nothing>()

    val isSuccess get() = this is Success
    val isError get() = this is Error

    fun getOrNull(): T? = (this as? Success)?.data

    fun getOrThrow(): T = when (this) {
        is Success -> data
        is Error -> throw cause ?: Exception("Result error: $code — $message")
    }

    fun <R> map(transform: (T) -> R): Result<R> = when (this) {
        is Success -> Success(transform(data))
        is Error -> this
    }

    suspend fun <R> mapSuspend(transform: suspend (T) -> R): Result<R> = when (this) {
        is Success -> Success(transform(data))
        is Error -> this
    }
}

enum class ErrorCode {
    // Network
    NETWORK_UNAVAILABLE,
    TIMEOUT,
    SERVER_ERROR,

    // Auth
    UNAUTHORIZED,
    FORBIDDEN,

    // Domain
    NOT_FOUND,
    VALIDATION_ERROR,
    RESUME_PARSE_FAILED,
    FILE_TOO_LARGE,
    UNSUPPORTED_FILE_TYPE,
    ANALYSIS_FAILED,

    // Generic
    UNKNOWN,
}

/**
 * Safely wrap a suspending block, converting exceptions to Result.Error.
 */
suspend fun <T> safeApiCall(block: suspend () -> T): Result<T> {
    return try {
        Result.Success(block())
    } catch (e: Exception) {
        Result.Error(
            code = ErrorCode.UNKNOWN,
            message = e.message ?: "Unknown error",
            cause = e,
        )
    }
}
