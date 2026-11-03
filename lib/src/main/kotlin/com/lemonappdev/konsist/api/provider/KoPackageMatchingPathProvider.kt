package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has a matching file path.
 */
interface KoPackageMatchingPathProvider : KoBaseProvider {
    /**
     * Determines whether the directory structure (file path) matches the package - the directory containing the file
     * ends with directories matching all package segments, e.g. package "com.app" matches "src/main/kotlin/com/app/A.kt",
     * but not "com/application/A.kt" or "com/app/feature/A.kt".
     */
    val hasMatchingPath: Boolean
}
