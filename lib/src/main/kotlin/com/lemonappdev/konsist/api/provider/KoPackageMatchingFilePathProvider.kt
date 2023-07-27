package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has a matching file path.
 */
interface KoPackageMatchingFilePathProvider : KoBaseProvider {
    /**
     * Whether the declaration has matching file path.
     */
    val hasMatchingFilePath: Boolean
}
