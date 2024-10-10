package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import org.jetbrains.kotlin.psi.psiUtil.isPublic

internal interface KoVisibilityModifierProviderCore :
    KoVisibilityModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasPublicModifier: Boolean
        get() = hasModifier(KoModifier.PUBLIC)

    override val hasPublicOrDefaultModifier: Boolean
        get() = ktModifierListOwner?.isPublic == true

    override val hasPrivateModifier: Boolean
        get() = hasModifier(KoModifier.PRIVATE)

    override val hasProtectedModifier: Boolean
        get() = hasModifier(KoModifier.PROTECTED)

    override val hasInternalModifier: Boolean
        get() = hasModifier(KoModifier.INTERNAL)
}
