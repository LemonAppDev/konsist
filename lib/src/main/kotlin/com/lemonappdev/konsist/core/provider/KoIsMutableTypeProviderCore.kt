package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsMutableTypeProvider
import com.lemonappdev.konsist.core.util.MutableTypeUtil

internal interface KoIsMutableTypeProviderCore :
    KoIsMutableTypeProvider,
    KoBaseProviderCore,
    KoSourceDeclarationProviderCore,
    KoTypeArgumentProviderCore {
    override val isMutableType: Boolean
        get() = MutableTypeUtil.isMutableType(sourceDeclaration)

    override val hasMutableTypeArgument: Boolean
        get() = MutableTypeUtil.hasMutableTypeArgument(sourceDeclaration, typeArguments)

    override val isImmutableType: Boolean
        get() = MutableTypeUtil.isImmutableType(sourceDeclaration)

    override val hasImmutableTypeArgument: Boolean
        get() = MutableTypeUtil.hasImmutableTypeArgument(sourceDeclaration, typeArguments)
}
