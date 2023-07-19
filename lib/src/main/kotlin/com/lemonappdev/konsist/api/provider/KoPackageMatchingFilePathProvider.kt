package com.lemonappdev.konsist.api.provider

interface KoPackageMatchingFilePathProvider: KoProvider {
    /**
     * Whether the declaration has matching file path.
     */
    val hasMatchingFilePath: Boolean
}
