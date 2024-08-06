package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoPackageMatchingPathProvider
import com.lemonappdev.konsist.core.util.PathUtil.separator

internal interface KoPackageMatchingPathProviderCore :
    KoPackageMatchingPathProvider,
    KoNameProviderCore,
    KoContainingFileProviderCore,
    KoPathProviderCore,
    KoBaseProviderCore {
    override val hasMatchingPath: Boolean
        get() =
            path
                .replace(separator, ".")
                .endsWith(name + "." + containingFile.nameWithExtension)
}
