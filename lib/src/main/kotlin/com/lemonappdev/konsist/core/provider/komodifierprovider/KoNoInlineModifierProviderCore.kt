package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoAbstractModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoDataModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoNoInlineModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoValueModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoNoInlineModifierProviderCore :
    KoNoInlineModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasNoInlineModifier: Boolean
        get() = hasModifiers(KoModifier.NOINLINE)
}
