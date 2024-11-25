package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsStarProjectionProvider
import org.jetbrains.kotlin.psi.KtTypeProjection

internal interface KoIsStarProjectionProviderCore :
    KoIsStarProjectionProvider,
    KoBaseProviderCore {
    val ktTypeProjection: KtTypeProjection?
        get() = null

    override val isStarProjection: Boolean
        get() = ktTypeProjection != null
}
