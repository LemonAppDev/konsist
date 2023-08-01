package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.provider.komodifierprovider.KoValModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import org.jetbrains.kotlin.psi.KtProperty

internal interface KoValModifierProviderCore : KoValModifierProvider, KoBaseProviderCore {
    val ktProperty: KtProperty
    
    override val hasValModifier: Boolean
        get() = !ktProperty.isVar
}
