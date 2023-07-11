package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoConstructorDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoConstructorsProvider

internal interface KoConstructorsProviderCore :
    KoConstructorsProvider,
    KoPrimaryConstructorProviderCore,
    KoSecondaryConstructorsProviderCore {
    override val allConstructors: List<KoConstructorDeclarationProvider>
        get() = listOfNotNull(primaryConstructor) + secondaryConstructors
}
