package com.compassai.core_domain.usecase.resume

import com.compassai.core_domain.model.Resume
import com.compassai.core_domain.repository.ResumeRepository
import com.compassai.core_domain.util.Result
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

/**
 * Use case: upload a resume file.
 *
 * A use case is a single business operation. It:
 * - Takes explicit inputs
 * - Delegates to the repository (never touches networking or DB directly)
 * - Returns a typed Result
 * - Contains zero UI logic
 *
 * Having one class per use case (rather than one big ResumeUseCases class)
 * makes each operation independently testable and follows the
 * Single Responsibility Principle.
 */
class UploadResumeUseCase @Inject constructor(
    private val resumeRepository: ResumeRepository,
) {
    suspend operator fun invoke(file: File, label: String? = null): Result<String> {
        // Validate file size before uploading (10MB limit from PRD FR-1)
        val maxSizeBytes = 10 * 1024 * 1024
        if (file.length() > maxSizeBytes) {
            return Result.Error(
                code = com.compassai.core_domain.util.ErrorCode.FILE_TOO_LARGE,
                message = "File size exceeds the 10MB limit.",
            )
        }

        val allowedMimeTypes = setOf(
            "application/pdf",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
        )
        val mimeType = file.guessMimeType()
        if (mimeType !in allowedMimeTypes) {
            return Result.Error(
                code = com.compassai.core_domain.util.ErrorCode.UNSUPPORTED_FILE_TYPE,
                message = "Only PDF and DOCX files are supported.",
            )
        }

        return resumeRepository.uploadResume(file, label)
    }

    private fun File.guessMimeType(): String {
        return when (extension.lowercase()) {
            "pdf" -> "application/pdf"
            "docx" -> "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
            else -> "application/octet-stream"
        }
    }
}

/**
 * Use case: observe a resume's parse status as a real-time Flow.
 * The ViewModel collects this and updates UI state as the backend parses.
 */
class ObserveResumeStatusUseCase @Inject constructor(
    private val resumeRepository: ResumeRepository,
) {
    operator fun invoke(resumeId: String): Flow<Resume> =
        resumeRepository.observeResumeStatus(resumeId)
}

/**
 * Use case: get the user's resume list.
 */
class GetResumesUseCase @Inject constructor(
    private val resumeRepository: ResumeRepository,
) {
    suspend operator fun invoke(): Result<List<Resume>> =
        resumeRepository.getResumes()
}
