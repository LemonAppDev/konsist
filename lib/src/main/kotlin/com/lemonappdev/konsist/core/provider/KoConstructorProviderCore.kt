package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoConstructorProvider

internal interface KoConstructorProviderCore :
    KoConstructorProvider,
    KoPrimaryConstructorProviderCore,
    KoSecondaryConstructorsProviderCore,
    KoBaseProviderCore {
    override val constructors: List<KoConstructorDeclaration>
        get() = listOfNotNull(primaryConstructor as? KoConstructorDeclaration) + secondaryConstructors

    override val numConstructors: Int
        get() = constructors.size

    override fun countConstructors(predicate: (KoConstructorDeclaration) -> Boolean): Int = constructors.count { predicate(it) }

    override fun hasConstructors(): Boolean = constructors.isNotEmpty()

    override fun hasConstructor(predicate: (KoConstructorDeclaration) -> Boolean): Boolean = constructors.any(predicate)

    override fun hasAllConstructors(predicate: (KoConstructorDeclaration) -> Boolean): Boolean = constructors.all(predicate)
}
