package com.lemonappdev.konsist.api.provider

interface KoPackageMatchingFilePathProvider {
    /**
     * Whether the declaration has matching file path.
     */
    val hasMatchingFilePath: Boolean
}
