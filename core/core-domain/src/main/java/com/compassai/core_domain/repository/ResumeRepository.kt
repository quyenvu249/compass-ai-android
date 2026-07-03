package com.compassai.core_domain.repository

import com.compassai.core_domain.model.Resume
import com.compassai.core_domain.model.ParsedResumeData
import com.compassai.core_domain.util.Result
import kotlinx.coroutines.flow.Flow
import java.io.File

/**
 * Resume repository interface.
 *
 * Defined in core-domain (no Android/Retrofit imports).
 * Implemented in core-data (where Retrofit and Room live).
 *
 * This inversion is what makes use cases testable with fake implementations
 * rather than mocked Retrofit calls.
 */
interface ResumeRepository {

    /**
     * Upload a resume file. Returns the resume ID immediately (async parse).
     */
    suspend fun uploadResume(file: File, label: String?): Result<String>

    /**
     * Get a resume by ID. Polls until parse is complete.
     */
    suspend fun getResume(resumeId: String): Result<Resume>

    /**
     * Observe resume parse status as a Flow — emits each time status changes.
     */
    fun observeResumeStatus(resumeId: String): Flow<Resume>

    /**
     * Update user corrections to parsed resume data.
     */
    suspend fun updateParsedData(
        resumeId: String,
        parsedData: ParsedResumeData,
        label: String?,
    ): Result<Resume>

    /**
     * Get all resumes for the current user.
     */
    suspend fun getResumes(): Result<List<Resume>>
}
