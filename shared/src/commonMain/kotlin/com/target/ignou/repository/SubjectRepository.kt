package com.target.ignou.repository

import com.target.ignou.model.AddSubjectRequest
import com.target.ignou.model.Subject
import com.target.ignou.model.SubjectResponse
import com.target.ignou.network.ApiClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class SubjectRepository {

    suspend fun getSubjects(
        course: String,
        semester: String   // 🔥 Int rakho, String nahi
    ): SubjectResponse {

        return ApiClient.httpClient.get("subjects") {
            parameter("course", course)
            parameter("semester", semester)
        }.body()
    }

    suspend fun addSubject(request: AddSubjectRequest): Subject {

        return ApiClient.httpClient.post("subjects") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}