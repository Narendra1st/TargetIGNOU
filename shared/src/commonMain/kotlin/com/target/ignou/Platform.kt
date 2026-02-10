package com.target.ignou

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
