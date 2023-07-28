package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.container.KoFile

/**
 * An interface representing a Kotlin scope that provides access to files.
 */
interface KoFileProvider : KoBaseProvider {
    /**
     * Sequence of the files.
     */
    val files: Sequence<KoFile>

    /**
     * Whether the scope has files.
     *
     * @param names the names of the files to check.
     * @return `true` if the scope has files with the specified names (or any file if [names] is empty), `false` otherwise.
     */
    fun hasFiles(vararg names: String): Boolean
}
