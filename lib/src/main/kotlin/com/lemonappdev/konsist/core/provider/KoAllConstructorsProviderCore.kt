package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoAllConstructorsProvider

internal interface KoAllConstructorsProviderCore :
    KoAllConstructorsProvider,
    KoPrimaryConstructorProviderCore,
    KoSecondaryConstructorsProviderCore,
    KoBaseProviderCore {
    override val allConstructors: Sequence<KoConstructorDeclaration>
        get() = sequenceOf(primaryConstructor as KoConstructorDeclaration) + secondaryConstructors
}
