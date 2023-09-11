package com.lemonappdev.konsist.api.provider

interface KoMatchesProvider: KoBaseProvider {
    fun matches(element: String): Boolean
}
