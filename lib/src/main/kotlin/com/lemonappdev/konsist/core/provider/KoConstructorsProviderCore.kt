package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoConstructorProvider
import com.lemonappdev.konsist.api.provider.KoConstructorsProvider

internal interface KoConstructorsProviderCore :
    KoConstructorsProvider,
    KoPrimaryConstructorProviderCore,
    KoSecondaryConstructorsProviderCore {
    override val allConstructors: List<KoConstructorProvider>
        get() = listOfNotNull(primaryConstructor) + secondaryConstructors
}
