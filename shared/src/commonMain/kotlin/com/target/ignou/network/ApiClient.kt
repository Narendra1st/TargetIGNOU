package com.target.ignou.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ApiClient {

    val httpClient = HttpClient {
        expectSuccess = true // Throws exception for non-2xx responses

        // 🔥 Base URL automatically add karega
        install(DefaultRequest) {
            url(getBaseUrl())
            contentType(ContentType.Application.Json)
        }

        // 🔥 JSON
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                }
            )
        }

        // 🔥 Logging (Debug ke liye)
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        // 🔥 Timeout
        install(HttpTimeout) {
            requestTimeoutMillis = 15000
            connectTimeoutMillis = 15000
            socketTimeoutMillis = 15000
        }
    }
}