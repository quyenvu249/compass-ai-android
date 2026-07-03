package com.compassai.core_domain.model

/**
 * Represents a user's uploaded and parsed resume.
 *
 * Field naming is deliberately profession-neutral:
 * - "organization" not "company" (covers hospitals, schools, freelance)
 * - "title" not "job title" (covers clinical, academic, freelance roles)
 * - "certifications" as a first-class field (not IT-centric — nurses,
 *    lawyers, accountants all have certifications that matter to roles)
 */
data class Resume(
    val id: String,
    val fileName: String,
    val label: String?,
    val parseStatus: ParseStatus,
    val parsedData: ParsedResumeData?,
    val isPrimary: Boolean,
    val createdAt: String,
)

data class ParsedResumeData(
    val contact: ContactInfo,
    val summary: String?,
    val experience: List<ExperienceEntry>,
    val education: List<EducationEntry>,
    val skills: List<String>,
    val certifications: List<String>,
    val otherSections: Map<String, String>,
)

data class ContactInfo(
    val name: String,
    val email: String?,
    val location: String?,
)

data class ExperienceEntry(
    val title: String,
    val organization: String,
    val duration: String,
    val description: String,
    val skillsMentioned: List<String>,
)

data class EducationEntry(
    val degree: String,
    val institution: String,
    val year: String?,
)

enum class ParseStatus {
    PENDING,
    COMPLETED,
    FAILED,
}
