package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider

internal interface KoContainingDeclarationProviderCore :
    KoContainingDeclarationProvider,
    KoContainingFileProviderCore,
    KoBaseProviderCore {
    override val containingDeclaration: KoBaseDeclaration
        get() = containingFile
}
