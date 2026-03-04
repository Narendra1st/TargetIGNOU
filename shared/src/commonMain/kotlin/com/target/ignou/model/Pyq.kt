package com.target.ignou.model


data class Pyq(
    val course: String,
    val year: Int,
    val subject: String,
    val pdfUrl: String
)
data class PyqResponse(
    val subjectCode: String,
    val pyqs: List<Pyq>
)
