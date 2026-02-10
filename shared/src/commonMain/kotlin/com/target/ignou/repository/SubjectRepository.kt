package com.target.ignou.repository

import com.target.ignou.data.remote.ApiClient

import com.target.ignou.model.SubjectResponse
import com.target.ignou.network.ApiClient
import io.ktor.client.call.*
import io.ktor.client.request.*

class SubjectRepository {

    suspend fun getSubjects(
        course: String,
        semester: Int
    ): SubjectResponse {
        return ApiClient.httpClient.get(
            "https://api.yourdomain.com/subjects"
        ) {
            parameter("course", course)
            parameter("semester", semester)
        }.body()
    }
}
