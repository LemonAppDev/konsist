package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
import com.lemonappdev.konsist.core.declaration.KoPrimaryConstructorDeclarationCore
import org.jetbrains.kotlin.psi.KtClass

internal interface KoPrimaryConstructorProviderCore : KoPrimaryConstructorProvider {
    val ktClass: KtClass

    override val primaryConstructor: KoPrimaryConstructorDeclaration?
        get() {
            val localPrimaryConstructor = ktClass.primaryConstructor

            return if (localPrimaryConstructor == null) {
                null
            } else {
                KoPrimaryConstructorDeclarationCore.getInstance(localPrimaryConstructor, parent)
            }
        }
}
