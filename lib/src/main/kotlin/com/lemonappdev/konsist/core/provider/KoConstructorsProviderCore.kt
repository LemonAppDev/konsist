package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoConstructorsProvider

internal interface KoConstructorsProviderCore :
    KoConstructorsProvider,
    KoPrimaryConstructorProviderCore,
    KoSecondaryConstructorsProviderCore {
    override val allConstructors: List<KoConstructorDeclaration>
        get() = listOfNotNull(primaryConstructor) + secondaryConstructors
}
