package com.target.ignou.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class SubjectResponse(
    val course: String,
    val semester: JsonElement,
    val subjects: List<Subject>
)
