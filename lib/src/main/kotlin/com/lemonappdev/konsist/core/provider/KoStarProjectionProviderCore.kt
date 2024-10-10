package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
import com.lemonappdev.konsist.api.provider.KoStarProjectionProvider

internal interface KoStarProjectionProviderCore :
    KoStarProjectionProvider,
    KoBaseProviderCore,
    KoSourceDeclarationProviderCore {
    override val isStarProjection: Boolean
        get() = sourceDeclaration is KoStarProjectionDeclaration
}
