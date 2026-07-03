package com.compassai.core.domain.model

/**
 * Represents a user's uploaded resume and its parsing state.
 *
 * Design notes:
 * - [parsedData] is nullable — it only exists after successful parsing.
 * - [parseStatus] drives UI state machine: pending → completed | failed.
 * - Field names are profession-neutral ("organization" not "company",
 *   "title" not "job title") to handle clinical, academic, and
 *   non-traditional career histories correctly.
 */
data class Resume(
    val id: String,
    val fileName: String,
    val label: String?,
    val parseStatus: ParseStatus,
    val parsedData: ResumeData?,
    val isPrimary: Boolean,
    val createdAt: String,
)

enum class ParseStatus {
    PENDING,
    COMPLETED,
    FAILED,
}

/**
 * Structured resume content extracted by the AI parsing layer.
 * All fields are optional — real-world resumes are inconsistent
 * and we degrade gracefully rather than failing on missing sections.
 */
data class ResumeData(
    val contact: ContactInfo?,
    val summary: String?,
    val experience: List<ExperienceItem>,
    val education: List<EducationItem>,
    val skills: List<String>,
    val certifications: List<String>,
)

data class ContactInfo(
    val name: String?,
    val email: String?,
    val location: String?,
)

data class ExperienceItem(
    val title: String,
    val organization: String,
    val duration: String?,
    val description: String?,
    val skillsMentioned: List<String>,
)

data class EducationItem(
    val degree: String,
    val institution: String,
    val year: String?,
)
