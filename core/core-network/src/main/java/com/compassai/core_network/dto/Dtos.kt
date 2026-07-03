package com.compassai.core_network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// ── Envelope ─────────────────────────────────────────────────────────────────

/**
 * Standard response envelope from the backend.
 * Every endpoint returns { success: bool, data: T?, error: ErrorDto? }
 */
@Serializable
data class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val error: ErrorDto? = null,
)

@Serializable
data class ErrorDto(
    val code: String,
    val message: String,
)

// ── Auth ──────────────────────────────────────────────────────────────────────

@Serializable
data class RegisterRequestDto(
    val email: String,
    val password: String,
    @SerialName("full_name") val fullName: String,
    val profession: String? = null,
)

@Serializable
data class LoginRequestDto(
    val email: String,
    val password: String,
)

@Serializable
data class AuthResponseDto(
    val user: UserDto,
    @SerialName("access_token") val accessToken: String,
    @SerialName("token_type") val tokenType: String,
)

@Serializable
data class TokenResponseDto(
    @SerialName("access_token") val accessToken: String,
    @SerialName("expires_in") val expiresIn: Int,
)

@Serializable
data class UserDto(
    val id: String,
    val email: String,
    @SerialName("full_name") val fullName: String?,
    val profession: String?,
)

// ── Resume ────────────────────────────────────────────────────────────────────

@Serializable
data class ResumeUploadResponseDto(
    @SerialName("resume_id") val resumeId: String,
    @SerialName("parse_status") val parseStatus: String,
    @SerialName("file_name") val fileName: String,
)

@Serializable
data class ResumeDto(
    val id: String,
    @SerialName("file_name") val fileName: String,
    val label: String? = null,
    @SerialName("parse_status") val parseStatus: String,
    @SerialName("parsed_data") val parsedData: ParsedResumeDataDto? = null,
    @SerialName("is_primary") val isPrimary: Boolean = false,
    @SerialName("created_at") val createdAt: String,
)

@Serializable
data class ResumesResponseDto(
    val resumes: List<ResumeDto>,
)

@Serializable
data class ParsedResumeDataDto(
    val contact: ContactInfoDto,
    val summary: String? = null,
    val experience: List<ExperienceEntryDto> = emptyList(),
    val education: List<EducationEntryDto> = emptyList(),
    val skills: List<String> = emptyList(),
    val certifications: List<String> = emptyList(),
    @SerialName("other_sections") val otherSections: Map<String, String> = emptyMap(),
)

@Serializable
data class ContactInfoDto(
    val name: String,
    val email: String? = null,
    val location: String? = null,
)

@Serializable
data class ExperienceEntryDto(
    val title: String,
    val organization: String,
    val duration: String,
    val description: String,
    @SerialName("skills_mentioned") val skillsMentioned: List<String> = emptyList(),
)

@Serializable
data class EducationEntryDto(
    val degree: String,
    val institution: String,
    val year: String? = null,
)

@Serializable
data class UpdateResumeRequestDto(
    @SerialName("parsed_data") val parsedData: ParsedResumeDataDto? = null,
    val label: String? = null,
)

// ── Job Description ───────────────────────────────────────────────────────────

@Serializable
data class SubmitJdRequestDto(
    @SerialName("raw_text") val rawText: String,
)

@Serializable
data class JdUploadResponseDto(
    @SerialName("jd_id") val jdId: String,
    @SerialName("parse_status") val parseStatus: String,
)

@Serializable
data class JobDescriptionDto(
    val id: String,
    val title: String? = null,
    val organization: String? = null,
    @SerialName("parse_status") val parseStatus: String,
    @SerialName("parsed_data") val parsedData: ParsedJdDataDto? = null,
    @SerialName("created_at") val createdAt: String,
)

@Serializable
data class ParsedJdDataDto(
    val title: String,
    val organization: String? = null,
    val seniority: String? = null,
    val domain: String? = null,
    @SerialName("required_skills") val requiredSkills: List<String> = emptyList(),
    @SerialName("preferred_skills") val preferredSkills: List<String> = emptyList(),
    val responsibilities: List<String> = emptyList(),
    val qualifications: List<String> = emptyList(),
)

// ── Analysis ──────────────────────────────────────────────────────────────────

@Serializable
data class StartAnalysisRequestDto(
    @SerialName("resume_id") val resumeId: String,
    @SerialName("jd_id") val jdId: String,
)

@Serializable
data class AnalysisStartResponseDto(
    @SerialName("analysis_id") val analysisId: String,
    val status: String,
)

@Serializable
data class AnalysisDto(
    val id: String,
    @SerialName("resume_id") val resumeId: String,
    @SerialName("job_description_id") val jobDescriptionId: String,
    @SerialName("match_score") val matchScore: Int? = null,
    val status: String,
    @SerialName("result_data") val resultData: AnalysisResultDto? = null,
    @SerialName("created_at") val createdAt: String,
)

@Serializable
data class AnalysisResultDto(
    val summary: String,
    @SerialName("match_score") val matchScore: Int,
    @SerialName("score_rationale") val scoreRationale: String,
    @SerialName("matched_strengths") val matchedStrengths: List<MatchedStrengthDto>,
    val gaps: List<SkillGapDto>,
    @SerialName("overall_recommendation") val overallRecommendation: String,
)

@Serializable
data class MatchedStrengthDto(
    @SerialName("resume_evidence") val resumeEvidence: String,
    @SerialName("jd_requirement") val jdRequirement: String,
    val explanation: String,
)

@Serializable
data class SkillGapDto(
    @SerialName("jd_requirement") val jdRequirement: String,
    val importance: String,
    val explanation: String,
    val suggestion: String,
)

@Serializable
data class AnalysisHistoryResponseDto(
    val analyses: List<AnalysisSummaryDto>,
    val total: Int,
)

@Serializable
data class AnalysisSummaryDto(
    val id: String,
    @SerialName("match_score") val matchScore: Int? = null,
    @SerialName("jd_title") val jdTitle: String? = null,
    @SerialName("jd_organization") val jdOrganization: String? = null,
    @SerialName("resume_label") val resumeLabel: String? = null,
    val status: String,
    @SerialName("created_at") val createdAt: String,
)
