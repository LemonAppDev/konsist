package com.lemonappdev.konsist.api.provider

interface KoPathProvider {
    /**
     * File path of the declaration.
     */
    val filePath: String

    /**
     * Project file path of the declaration.
     */
    val projectFilePath: String

    /**
     * Whatever declaration reside in file path.
     *
     * @param path the file path to check.
     * @param absolutePath Flag indicating whether the provided path is an absolute path.
     *                    If set to `true`, the `path` parameter represents an absolute path.
     *                    If set to `false` (default), the `path` parameter represents a relative path.
     * @return `true` if the declaration resides in the specified file path, `false` otherwise.
     */
    fun resideInFilePath(path: String, absolutePath: Boolean = false): Boolean
}
