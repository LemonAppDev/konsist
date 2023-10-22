package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has a matching file path.
 */
interface KoPackageMatchingPathProvider : KoBaseProvider {
    /**
     * Determines whatever the directory structure (file path) matches the package.
     */
    val hasMatchingPath: Boolean
}
