package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoPackageMatchingFilePathProvider

internal interface KoPackageMatchingFilePathProviderCore :
    KoPackageMatchingFilePathProvider,
    KoFullyQualifiedNameProviderCore,
    KoContainingFileProviderCore,
    KoPathProviderCore {
    override val hasMatchingFilePath: Boolean
        get() =
        filePath
            .replace("/", ".")
            .endsWith(fullyQualifiedName + "." + containingFile.nameWithExtension)
}
