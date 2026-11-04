package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has a package.
 */
interface KoHasPackageProvider : KoBaseProvider {
    /**
     * Determines whether the package matches the directory structure (file path) - the directory containing the file
     * ends with directories matching all package segments, e.g. package "com.app" matches "src/main/kotlin/com/app/A.kt",
     * but not "com/application/A.kt" or "com/app/feature/A.kt".
     */
    val hasMatchingPackage: Boolean

    /**
     * Determines whether the declaration has the specified package.
     *
     * @param name the name of the package to check.
     * @return `true` if the declaration has a package with the specified name, `false` otherwise.
     */
    fun hasPackage(name: String): Boolean
}
