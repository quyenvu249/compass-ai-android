package com.compassai.core_network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Provider

/**
 * OkHttp interceptor that attaches the Bearer JWT token to every request.
 *
 * Uses Provider<String?> (lazy injection) rather than String? directly to
 * avoid a circular dependency: NetworkModule needs the interceptor, and the
 * interceptor needs the token — Provider breaks the cycle by deferring
 * token resolution to request time, not construction time.
 *
 * The token provider is implemented in core-data (TokenStorage) and
 * injected via Hilt.
 */
class AuthInterceptor @Inject constructor(
    private val tokenProvider: Provider<@JvmSuppressWildcards String?>,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenProvider.get()
        val request = if (token != null) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }
        return chain.proceed(request)
    }
}
