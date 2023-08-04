package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoPackageMatchingPathProvider

internal interface KoPackageMatchingPathProviderCore :
    KoPackageMatchingPathProvider,
    KoFullyQualifiedNameProviderCore,
    KoContainingFileProviderCore,
    KoPathProviderCore,
    KoBaseProviderCore {
    override val hasMatchingPath: Boolean
        get() =
            path
                .replace("/", ".")
                .endsWith(fullyQualifiedName + "." + containingFile.nameWithExtension)
}
