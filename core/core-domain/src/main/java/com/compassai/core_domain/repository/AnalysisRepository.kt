package com.compassai.core_domain.repository

import com.compassai.core_domain.model.Analysis
import com.compassai.core_domain.model.JobDescription
import com.compassai.core_domain.util.Result
import kotlinx.coroutines.flow.Flow

interface AnalysisRepository {

    suspend fun submitJobDescription(rawText: String): Result<String>

    suspend fun getJobDescription(jdId: String): Result<JobDescription>

    fun observeJobDescriptionStatus(jdId: String): Flow<JobDescription>

    suspend fun startAnalysis(resumeId: String, jdId: String): Result<String>

    suspend fun getAnalysis(analysisId: String): Result<Analysis>

    fun observeAnalysisStatus(analysisId: String): Flow<Analysis>

    suspend fun getAnalysisHistory(): Result<List<Analysis>>
}
