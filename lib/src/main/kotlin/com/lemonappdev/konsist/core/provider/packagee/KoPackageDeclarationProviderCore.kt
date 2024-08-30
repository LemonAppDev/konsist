package com.lemonappdev.konsist.core.provider.packagee

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore

internal interface KoPackageDeclarationProviderCore :
    KoPackageProviderCore,
    KoContainingFileProviderCore {
    override val packagee: KoPackageDeclaration?
        get() = containingFile.packagee
}
