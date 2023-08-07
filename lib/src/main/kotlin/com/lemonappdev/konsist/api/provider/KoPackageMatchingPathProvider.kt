package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has a matching file path.
 */
interface KoPackageMatchingPathProvider : KoBaseProvider {
    /**
     * Whether the declaration has matching file path.
     */
    val hasMatchingPath: Boolean
}
