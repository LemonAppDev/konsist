package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoVarAndValProvider
import org.jetbrains.kotlin.psi.KtProperty

internal interface KoVarAndValProviderCore : KoVarAndValProvider {
    val ktProperty: KtProperty

    override val isVar: Boolean
        get() = ktProperty.isVar

    override val isVal: Boolean
        get() = !ktProperty.isVar
}
