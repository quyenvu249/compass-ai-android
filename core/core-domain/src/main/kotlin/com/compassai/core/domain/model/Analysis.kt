package com.compassai.core.domain.model

/**
 * Represents one resume-vs-job-description analysis run.
 *
 * An Analysis is immutable once completed — it is an audit record
 * of what the AI produced at a point in time, with a specific prompt version.
 * This immutability is enforced at the DB layer (no soft delete on analyses).
 */
data class Analysis(
    val id: String,
    val resumeId: String,
    val jobDescriptionId: String,
    val status: AnalysisStatus,
    val result: AnalysisResult?,
    val createdAt: String,
)

enum class AnalysisStatus {
    PENDING,
    COMPLETED,
    FAILED,
}

/**
 * The structured output of one AI analysis run.
 *
 * [matchScore] is 0-100 with a written [scoreRationale] — never shown
 * as a bare number in the UI.
 *
 * [matchedStrengths] and [gaps] are the core value: specific, cited
 * comparisons between resume evidence and JD requirements.
 */
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

/**
 * Lightweight summary used in history list screens.
 * Avoids loading the full [AnalysisResult] when only list metadata is needed.
 */
data class AnalysisSummary(
    val id: String,
    val matchScore: Int?,
    val jdTitle: String?,
    val jdOrganization: String?,
    val resumeLabel: String?,
    val status: AnalysisStatus,
    val createdAt: String,
)
