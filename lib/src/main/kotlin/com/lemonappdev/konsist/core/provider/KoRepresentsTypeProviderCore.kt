package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider

internal interface KoRepresentsTypeProviderCore :
    KoRepresentsTypeProvider,
    KoNameProviderCore,
    KoFullyQualifiedNameProviderCore,
    KoBaseProviderCore {
    override fun representsType(name: String?): Boolean = name == this.name || name == fullyQualifiedName
}
