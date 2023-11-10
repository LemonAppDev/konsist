package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
import com.lemonappdev.konsist.core.declaration.KoPrimaryConstructorDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtClass

internal interface KoPrimaryConstructorProviderCore :
    KoPrimaryConstructorProvider,
    KoContainingDeclarationProviderCore,
    KoBaseProviderCore {
    val ktClass: KtClass

    override val primaryConstructor: KoPrimaryConstructorDeclaration?
        get() {
            val localPrimaryConstructor = ktClass.primaryConstructor ?: return null

            return KoPrimaryConstructorDeclarationCore.getInstance(localPrimaryConstructor, this.castToKoBaseDeclaration())
        }

    override val hasPrimaryConstructor: Boolean
        get() = ktClass.hasExplicitPrimaryConstructor()
}
