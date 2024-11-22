package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoStarProjectionProvider
import org.jetbrains.kotlin.psi.KtTypeProjection

internal interface KoStarProjectionProviderCore :
    KoStarProjectionProvider,
    KoBaseProviderCore {
    val ktTypeProjection: KtTypeProjection?

    override val isStarProjection: Boolean
        get() = ktTypeProjection != null
}
