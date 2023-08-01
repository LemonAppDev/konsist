package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.provider.komodifierprovider.KoValModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoVarModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import org.jetbrains.kotlin.psi.KtProperty

internal interface KoVarModifierProviderCore : KoVarModifierProvider, KoBaseProviderCore {
    val ktProperty: KtProperty

    override val hasVarModifier: Boolean
        get() = ktProperty.isVar
}
