package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoSetterDeclaration
import com.lemonappdev.konsist.api.provider.KoSetterProvider
import com.lemonappdev.konsist.core.declaration.KoSetterDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtPropertyAccessor

internal interface KoSetterProviderCore : KoSetterProvider, KoBaseProviderCore, KoContainingDeclarationProviderCore {
    val ktCallableDeclaration: KtCallableDeclaration

    private val ktPropertyAccessor: List<KtPropertyAccessor>
        get() =
            ktCallableDeclaration
                .children
                .filterIsInstance<KtPropertyAccessor>()

    override val setter: KoSetterDeclaration?
        get() =
            ktPropertyAccessor
                .firstOrNull { it.isSetter }
                ?.let { KoSetterDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }

    override val hasSetter: Boolean
        get() = setter != null
}
