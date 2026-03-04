package com.target.ignou.model

import kotlinx.serialization.Serializable

@Serializable
data class SolvedPaper(
    val subject: String,
    val year: Int,
    val content: String
)
