package com.target.ignou.model

import kotlinx.serialization.Serializable

@Serializable
data class SubjectResponse(
    val course: String,
    val semester: Int,
    val subjects: List<Subject>
)
