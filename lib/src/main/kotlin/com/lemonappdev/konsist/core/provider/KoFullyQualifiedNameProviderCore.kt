package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.core.util.TypeResolver

internal interface KoFullyQualifiedNameProviderCore :
    KoFullyQualifiedNameProvider,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoNameProviderCore {
    val stringUsedAsFullyQualifiedName: String
        get() = name

    override val fullyQualifiedName: String?
        get() =
            TypeResolver.resolveFullyQualifiedName(stringUsedAsFullyQualifiedName, containingFile)
                // Fallback: return the name if the type cannot be resolved
                ?: stringUsedAsFullyQualifiedName
}
