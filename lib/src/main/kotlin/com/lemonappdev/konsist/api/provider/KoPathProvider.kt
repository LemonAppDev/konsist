package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to its path information.
 */
interface KoPathProvider : KoBaseProvider {
    /**
     * File path of the declaration or path of the file.
     */
    val path: String

    /**
     * Project file path of the declaration or root project path of the file.
     */
    val projectPath: String

    /**
     * Determines whatever declaration reside in file path or file reside in path.
     *
     * @param path the (file) path to check.
     * @param absolutePath Flag indicating whether the provided path is an absolute path.
     *                    If set to `true`, the `path` parameter represents an absolute path.
     *                    If set to `false` (default), the `path` parameter represents a relative path.
     * @return `true` if the declaration resides in the specified (file) path, `false` otherwise.
     */
    fun resideInPath(path: String, absolutePath: Boolean = false): Boolean
}
