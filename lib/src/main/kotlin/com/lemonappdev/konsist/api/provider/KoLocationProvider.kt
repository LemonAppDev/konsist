package com.lemonappdev.konsist.api.provider

interface KoLocationProvider : KoBaseProvider {
    /**
     * Location of the declaration containing the file path, line and column.
     */
    val location: String

    /**
     * Text of the declaration with the location (file path, line and column).
     */
    val locationWithText: String
}
