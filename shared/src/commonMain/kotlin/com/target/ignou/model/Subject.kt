package com.target.ignou.model

import kotlinx.serialization.Serializable

@Serializable
data class Subject(
    val code: String,
    val name: String
)
