package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoAnnotationModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoAnnotationModifierProviderCore :
    KoAnnotationModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasAnnotationModifier: Boolean
        get() = hasModifiers(KoModifier.ANNOTATION)
}
