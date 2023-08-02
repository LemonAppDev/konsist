package com.lemonappdev.konsist.core.provider.packagee

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration

internal interface KoPackageDeclarationProviderCore : KoPackageProviderCore {
    override val packagee: KoPackageDeclaration?
        get() = containingFile.packagee
}
