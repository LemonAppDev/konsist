package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoPackageMatchingPathProvider
import com.lemonappdev.konsist.core.util.PathUtil.sep

internal interface KoPackageMatchingPathProviderCore :
    KoPackageMatchingPathProvider,
    KoFullyQualifiedNameProviderCore,
    KoContainingFileProviderCore,
    KoPathProviderCore,
    KoBaseProviderCore {
    override val hasMatchingPath: Boolean
        get() =
            path
                .replace(sep, ".")
                .endsWith(fullyQualifiedName + "." + containingFile.nameWithExtension)
}
