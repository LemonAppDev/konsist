package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoExtensionProvider
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

internal interface KoExtensionProviderCore : KoExtensionProvider, KoBaseProviderCore {
    val ktCallableDeclaration: KtCallableDeclaration

    override val isExtension: Boolean
        get() = ktCallableDeclaration.isExtensionDeclaration()
}
