package com.sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
