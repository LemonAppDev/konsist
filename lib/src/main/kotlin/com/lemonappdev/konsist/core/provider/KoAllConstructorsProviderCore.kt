package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoAllConstructorsProvider

internal interface KoAllConstructorsProviderCore :
    KoAllConstructorsProvider,
    KoPrimaryConstructorProviderCore,
    KoSecondaryConstructorsProviderCore,
    KoBaseProviderCore {
    override val allConstructors: List<KoConstructorDeclaration>
        get() = listOf(primaryConstructor as KoConstructorDeclaration) + secondaryConstructors
}
