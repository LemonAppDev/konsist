package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
import com.lemonappdev.konsist.core.declaration.KoPrimaryConstructorDeclarationImpl
import org.jetbrains.kotlin.psi.KtClass

internal interface KoPrimaryConstructorProviderCore :
    KoPrimaryConstructorProvider,
    KoParentProvider {
    val ktClass: KtClass

    override val primaryConstructor: KoPrimaryConstructorDeclaration?
        get() {
            val localPrimaryConstructor = ktClass.primaryConstructor ?: return null

            return KoPrimaryConstructorDeclarationImpl.getInstance(localPrimaryConstructor, this)
        }

    override fun hasPrimaryConstructor(): Boolean = ktClass.hasExplicitPrimaryConstructor()
}
