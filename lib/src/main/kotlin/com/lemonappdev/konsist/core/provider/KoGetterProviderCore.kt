package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoGetterDeclaration
import com.lemonappdev.konsist.api.provider.KoBodyProvider
import com.lemonappdev.konsist.api.provider.KoGetterProvider
import com.lemonappdev.konsist.core.declaration.KoGetterDeclarationCore
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtDeclarationWithBody
import org.jetbrains.kotlin.psi.KtPropertyAccessor
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

internal interface KoGetterProviderCore : KoGetterProvider, KoBaseProviderCore, KoContainingDeclarationProviderCore {
    val ktCallableDeclaration: KtCallableDeclaration

    private val ktPropertyAccessor: List<KtPropertyAccessor>
        get() = ktCallableDeclaration
            .children
            .filterIsInstance<KtPropertyAccessor>()

    override val getter: KoGetterDeclaration?
        get() = ktPropertyAccessor
            .firstOrNull { it.isGetter }
            ?.let { KoGetterDeclarationCore.getInstance(it, this) }

    override val hasGetter: Boolean
        get() = getter != null
}
