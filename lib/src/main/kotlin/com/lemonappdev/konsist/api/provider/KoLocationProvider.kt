package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about its location,
 * including file path, line, and column.
 */
interface KoLocationProvider : KoBaseProvider {
    /**
     * Location of the declaration containing the file path, line and column.
     * File path uses OS-specific separators (`\` on Windows, `/` on other OSes).
     */
    val location: String

    /**
     * Text of the declaration with the location (file path, line and column).
     * File path uses OS-specific separators (`\` on Windows, `/` on other OSes).
     */
    val locationWithText: String
}
