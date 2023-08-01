package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
import com.lemonappdev.konsist.core.declaration.KoPrimaryConstructorDeclarationImpl
import org.jetbrains.kotlin.psi.KtClass

internal interface KoPrimaryConstructorProviderCore :
    KoPrimaryConstructorProvider,
    KoParentProviderCore,
    KoBaseProviderCore {
    val ktClass: KtClass

    override val primaryConstructor: KoPrimaryConstructorDeclaration?
        get() {
            val localPrimaryConstructor = ktClass.primaryConstructor ?: return null

            return KoPrimaryConstructorDeclarationImpl.getInstance(localPrimaryConstructor, this)
        }

    override val hasPrimaryConstructor: Boolean
        get() = ktClass.hasExplicitPrimaryConstructor()
}
