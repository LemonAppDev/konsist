package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoConstructorProvider
import com.lemonappdev.konsist.api.provider.KoAllConstructorsProvider

internal interface KoAllConstructorsProviderCore :
    KoAllConstructorsProvider,
    KoPrimaryConstructorProviderCore,
    KoSecondaryConstructorsProviderCore,
    KoBaseProviderCore {
    override val allConstructors: Sequence<KoConstructorProvider>
        get() = sequenceOf(primaryConstructor as KoConstructorProvider) + secondaryConstructors
}
