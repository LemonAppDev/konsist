package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoSecondaryConstructorsProvider
import com.lemonappdev.konsist.core.declaration.KoSecondaryConstructorDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtClass

internal interface KoSecondaryConstructorsProviderCore :
    KoSecondaryConstructorsProvider,
    KoContainingDeclarationProviderCore,
    KoBaseProviderCore {
    val ktClass: KtClass

    override val secondaryConstructors: List<KoSecondaryConstructorDeclaration>
        get() =
            ktClass
                .secondaryConstructors
                .map { KoSecondaryConstructorDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }

    override val numSecondaryConstructors: Int
        get() = secondaryConstructors.size

    override fun countSecondaryConstructors(predicate: (KoSecondaryConstructorDeclaration) -> Boolean): Int =
        secondaryConstructors.count { predicate(it) }

    override fun hasSecondaryConstructors(): Boolean = secondaryConstructors.isNotEmpty()

    override fun hasSecondaryConstructor(predicate: (KoSecondaryConstructorDeclaration) -> Boolean): Boolean =
        secondaryConstructors.any(predicate)

    override fun hasAllSecondaryConstructors(predicate: (KoSecondaryConstructorDeclaration) -> Boolean): Boolean =
        secondaryConstructors.all(predicate)
}
