package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoAbstractModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoDataModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoValueModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoVisibilityModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import org.jetbrains.kotlin.psi.psiUtil.isPublic

internal interface KoVisibilityModifierProviderCore :
    KoVisibilityModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasPublicModifier: Boolean
        get() = hasModifiers(KoModifier.PUBLIC)

    override val isPublicOrDefault: Boolean
        get() = ktTypeParameterListOwner.isPublic

    override val hasPrivateModifier: Boolean
        get() = hasModifiers(KoModifier.PRIVATE)

    override val hasProtectedModifier: Boolean
        get() = hasModifiers(KoModifier.PROTECTED)

    override val hasInternalModifier: Boolean
        get() = hasModifiers(KoModifier.INTERNAL)
}
