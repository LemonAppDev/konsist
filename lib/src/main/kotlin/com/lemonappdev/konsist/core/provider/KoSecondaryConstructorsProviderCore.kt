package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoSecondaryConstructorsProvider
import com.lemonappdev.konsist.core.declaration.KoSecondaryConstructorDeclarationImpl
import org.jetbrains.kotlin.psi.KtClass

internal interface KoSecondaryConstructorsProviderCore :
    KoSecondaryConstructorsProvider,
    KoParentProviderCore,
    KoBaseProviderCore {
    val ktClass: KtClass

    override val secondaryConstructors: Sequence<KoSecondaryConstructorDeclaration>
        get() =
            ktClass
                .secondaryConstructors
                .map { KoSecondaryConstructorDeclarationImpl.getInstance(it, this) }
                .asSequence()

    override val numSecondaryConstructors: Int
        get() = secondaryConstructors.toList().size

    override fun hasSecondaryConstructors(): Boolean = ktClass.hasSecondaryConstructors()
}
