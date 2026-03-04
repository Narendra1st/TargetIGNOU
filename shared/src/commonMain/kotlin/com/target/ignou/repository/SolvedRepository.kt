package com.target.ignou.repository

import com.target.ignou.model.SolvedPaper
import com.target.ignou.network.ApiClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class SolvedRepository {
    suspend fun getSolvedPaper(subject: String, year: Int): SolvedPaper {
        return ApiClient.httpClient.get("solved") {
            parameter("subject", subject)
            parameter("year", year) // Kept as Int as requested or implied
        }.body()
    }
}
