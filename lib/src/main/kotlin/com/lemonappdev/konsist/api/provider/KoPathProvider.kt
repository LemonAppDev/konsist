package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to its path information.
 */
interface KoPathProvider : KoBaseProvider {
    /**
     * File path of the declaration or path of the file.
     * Uses OS-specific separators (`\` on Windows, `/` on other OSes),
     * e.g. `C:\project\app\src\main\kotlin\SampleClass.kt` on Windows.
     * To check path portably, use [resideInPath] which accepts both `/` and `\` separators.
     */
    val path: String

    /**
     * Project file path of the declaration or root project path of the file.
     * Uses OS-specific separators (`\` on Windows, `/` on other OSes),
     * e.g. `\app\src\main\kotlin\SampleClass.kt` on Windows.
     * To check path portably, use [resideInPath] which accepts both `/` and `\` separators.
     */
    val projectPath: String

    /**
     * Determines whether the declaration (or file) resides in the specified path.
     *
     * @param path the (file) path to check. Both `/` and `\` separators are accepted on all OSes.
     * @param absolutePath Flag indicating whether the provided path is an absolute path.
     *                    If set to `true`, the `path` parameter represents an absolute path.
     *                    If set to `false` (default), the `path` parameter represents a relative path.
     * @return `true` if the declaration resides in the specified (file) path, `false` otherwise.
     */
    fun resideInPath(
        path: String,
        absolutePath: Boolean = false,
    ): Boolean
}
