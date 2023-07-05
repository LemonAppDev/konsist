package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration
import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoConstructorProvider
import com.lemonappdev.konsist.core.declaration.KoPrimaryConstructorDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoSecondaryConstructorDeclarationImpl
import org.jetbrains.kotlin.psi.KtClass

internal interface KoConstructorProviderCore : KoConstructorProvider {
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

    override val secondaryConstructors: List<KoSecondaryConstructorDeclaration>
        get() = ktClass
            .secondaryConstructors
            .map { KoSecondaryConstructorDeclarationImpl.getInstance(it, parent) }

    override val allConstructors: List<KoConstructorDeclaration>
        get() = listOfNotNull(primaryConstructor) + secondaryConstructors
}
