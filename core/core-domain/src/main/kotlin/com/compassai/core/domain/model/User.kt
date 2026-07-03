package com.compassai.core.domain.model

/**
 * Core domain model representing an authenticated user.
 *
 * Deliberately minimal — only what the domain layer needs.
 * UI display concerns (formatted names, avatar URLs) belong
 * in the presentation layer, not here.
 */
data class User(
    val id: String,
    val email: String,
    val fullName: String?,
    val profession: String?,
)
