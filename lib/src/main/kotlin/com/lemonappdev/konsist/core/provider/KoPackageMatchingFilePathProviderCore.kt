package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoPackageMatchingFilePathProvider

internal interface KoPackageMatchingFilePathProviderCore :
    KoPackageMatchingFilePathProvider,
    KoFullyQualifiedNameProviderCore,
    KoContainingFileProviderCore,
    KoPathProviderCore,
    KoBaseProviderCore {
    override val hasMatchingFilePath: Boolean
        get() =
            path
                .replace("/", ".")
                .endsWith(fullyQualifiedName + "." + containingFile.nameWithExtension)
}
