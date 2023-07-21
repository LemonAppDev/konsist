package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoConstructorProvider
import com.lemonappdev.konsist.api.provider.KoConstructorsProvider

internal interface KoConstructorsProviderCore :
    KoConstructorsProvider,
    KoPrimaryConstructorProviderCore,
    KoSecondaryConstructorsProviderCore,
    KoBaseProviderCore {
    override val allConstructors: Sequence<KoConstructorProvider>
        get() = sequenceOf(primaryConstructor as KoConstructorProvider) + secondaryConstructors
}
