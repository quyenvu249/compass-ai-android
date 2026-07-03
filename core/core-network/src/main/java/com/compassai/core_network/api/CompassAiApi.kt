package com.compassai.core_network.api

import com.compassai.core_network.dto.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

/**
 * Retrofit API interface — mirrors the OpenAPI contract exactly.
 *
 * Every endpoint returns Response<ApiResponse<T>> so we can inspect
 * both the HTTP status code and the response envelope in the repository layer.
 * We never throw on error responses — error handling is explicit via Result<T>.
 */
interface CompassAiApi {

    // ── Auth ──────────────────────────────────────────────────────────────────

    @POST("v1/auth/register")
    suspend fun register(
        @Body request: RegisterRequestDto,
    ): Response<ApiResponse<AuthResponseDto>>

    @POST("v1/auth/login")
    suspend fun login(
        @Body request: LoginRequestDto,
    ): Response<ApiResponse<AuthResponseDto>>

    @POST("v1/auth/refresh")
    suspend fun refreshToken(): Response<ApiResponse<TokenResponseDto>>

    @POST("v1/auth/logout")
    suspend fun logout(): Response<ApiResponse<Unit>>

    // ── Resume ────────────────────────────────────────────────────────────────

    @Multipart
    @POST("v1/resumes")
    suspend fun uploadResume(
        @Part file: MultipartBody.Part,
        @Part("label") label: RequestBody?,
    ): Response<ApiResponse<ResumeUploadResponseDto>>

    @GET("v1/resumes/{resumeId}")
    suspend fun getResume(
        @Path("resumeId") resumeId: String,
    ): Response<ApiResponse<ResumeDto>>

    @PATCH("v1/resumes/{resumeId}")
    suspend fun updateResume(
        @Path("resumeId") resumeId: String,
        @Body request: UpdateResumeRequestDto,
    ): Response<ApiResponse<ResumeDto>>

    @GET("v1/resumes")
    suspend fun getResumes(): Response<ApiResponse<ResumesResponseDto>>

    // ── Job Descriptions ──────────────────────────────────────────────────────

    @POST("v1/job-descriptions")
    suspend fun submitJobDescription(
        @Body request: SubmitJdRequestDto,
    ): Response<ApiResponse<JdUploadResponseDto>>

    @GET("v1/job-descriptions/{jdId}")
    suspend fun getJobDescription(
        @Path("jdId") jdId: String,
    ): Response<ApiResponse<JobDescriptionDto>>

    // ── Analysis ──────────────────────────────────────────────────────────────

    @POST("v1/analyses")
    suspend fun startAnalysis(
        @Body request: StartAnalysisRequestDto,
    ): Response<ApiResponse<AnalysisStartResponseDto>>

    @GET("v1/analyses/{analysisId}")
    suspend fun getAnalysis(
        @Path("analysisId") analysisId: String,
    ): Response<ApiResponse<AnalysisDto>>

    @GET("v1/analyses")
    suspend fun getAnalysisHistory(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ): Response<ApiResponse<AnalysisHistoryResponseDto>>
}
