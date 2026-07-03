package com.compassai.core_domain.usecase.analysis

import com.compassai.core_domain.model.Analysis
import com.compassai.core_domain.model.JobDescription
import com.compassai.core_domain.repository.AnalysisRepository
import com.compassai.core_domain.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubmitJobDescriptionUseCase @Inject constructor(
    private val analysisRepository: AnalysisRepository,
) {
    suspend operator fun invoke(rawText: String): Result<String> {
        if (rawText.isBlank()) {
            return Result.Error(
                code = com.compassai.core_domain.util.ErrorCode.VALIDATION_ERROR,
                message = "Job description cannot be empty.",
            )
        }
        if (rawText.length < 50) {
            return Result.Error(
                code = com.compassai.core_domain.util.ErrorCode.VALIDATION_ERROR,
                message = "Job description is too short to analyse meaningfully.",
            )
        }
        return analysisRepository.submitJobDescription(rawText)
    }
}

class ObserveJobDescriptionStatusUseCase @Inject constructor(
    private val analysisRepository: AnalysisRepository,
) {
    operator fun invoke(jdId: String): Flow<JobDescription> =
        analysisRepository.observeJobDescriptionStatus(jdId)
}

class StartAnalysisUseCase @Inject constructor(
    private val analysisRepository: AnalysisRepository,
) {
    suspend operator fun invoke(resumeId: String, jdId: String): Result<String> =
        analysisRepository.startAnalysis(resumeId, jdId)
}

class ObserveAnalysisUseCase @Inject constructor(
    private val analysisRepository: AnalysisRepository,
) {
    operator fun invoke(analysisId: String): Flow<Analysis> =
        analysisRepository.observeAnalysisStatus(analysisId)
}

class GetAnalysisHistoryUseCase @Inject constructor(
    private val analysisRepository: AnalysisRepository,
) {
    suspend operator fun invoke(): Result<List<Analysis>> =
        analysisRepository.getAnalysisHistory()
}
