package com.target.ignou.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Subject(
    val _id: String,
    val code: String,
    val name: String,
    val course: String,
    val semester: JsonElement
)

@Serializable
data class AddSubjectRequest(
    val code: String,
    val name: String,
    val course: String,
    val semester: Int   // ✅ FIXED
)