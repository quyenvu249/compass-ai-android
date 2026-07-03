package com.compassai.core_domain.model

/**
 * Represents a parsed job description.
 *
 * "domain" captures the professional field (Healthcare, Education, Legal,
 * Finance, etc.) — extracted by the AI layer from context, never hard-coded
 * from a dropdown. This powers profession-adaptive behavior downstream.
 */
data class JobDescription(
    val id: String,
    val title: String?,
    val organization: String?,
    val parseStatus: ParseStatus,
    val parsedData: ParsedJdData?,
    val createdAt: String,
)

data class ParsedJdData(
    val title: String,
    val organization: String?,
    val seniority: String?,
    val domain: String?,
    val requiredSkills: List<String>,
    val preferredSkills: List<String>,
    val responsibilities: List<String>,
    val qualifications: List<String>,
)

/**
 * The result of an AI-driven resume vs. job description analysis.
 *
 * Every analysis is immutable once created — it represents the AI's
 * assessment at a specific point in time with a specific prompt version.
 * Users can re-run analysis (creating a new Analysis) but cannot edit
 * an existing one.
 */
data class Analysis(
    val id: String,
    val resumeId: String,
    val jobDescriptionId: String,
    val matchScore: Int?,
    val status: AnalysisStatus,
    val result: AnalysisResult?,
    val createdAt: String,
)

data class AnalysisResult(
    val summary: String,
    val matchScore: Int,
    val scoreRationale: String,
    val matchedStrengths: List<MatchedStrength>,
    val gaps: List<SkillGap>,
    val overallRecommendation: String,
)

data class MatchedStrength(
    val resumeEvidence: String,
    val jdRequirement: String,
    val explanation: String,
)

data class SkillGap(
    val jdRequirement: String,
    val importance: GapImportance,
    val explanation: String,
    val suggestion: String,
)

enum class GapImportance {
    HIGH,
    MEDIUM,
    LOW,
}

enum class AnalysisStatus {
    PENDING,
    COMPLETED,
    FAILED,
}
