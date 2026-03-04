package com.target.ignou.repository

import com.target.ignou.model.PyqResponse
import com.target.ignou.network.ApiClient
import com.target.ignou.network.getBaseUrl
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PyqRepository {

    suspend fun getPyqs(subjectCode: String): PyqResponse {
        val baseUrl: String = getBaseUrl().trimEnd('/')
        return ApiClient.httpClient.get(
            "$baseUrl/pyqs"
        ) {
            parameter("subjectCode", subjectCode)
        }.body()
    }
}
