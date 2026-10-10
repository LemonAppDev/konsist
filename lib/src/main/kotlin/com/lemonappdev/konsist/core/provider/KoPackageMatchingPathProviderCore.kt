package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoPackageMatchingPathProvider

internal interface KoPackageMatchingPathProviderCore :
    KoPackageMatchingPathProvider,
    KoNameProviderCore,
    KoContainingFileProviderCore,
    KoPathProviderCore,
    KoBaseProviderCore {
    override val hasMatchingPath: Boolean
        get() =
            path
                .replace("/", ".")
                .endsWith(name + "." + containingFile.nameWithExtension)
}
