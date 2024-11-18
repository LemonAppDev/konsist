package com.lemonappdev.konsist.core.declaration.type

import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.core.declaration.KoBaseSourceDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoSourceDeclarationCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationCastProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageProviderCore

internal interface KoBaseTypeDeclarationCore :
    KoBaseTypeDeclaration,
    KoBaseSourceDeclarationCore,
    KoSourceDeclarationCore,
    KoBaseProviderCore,
    KoNameProviderCore,
    KoTextProviderCore,
    KoPackageProviderCore,
    KoResideInPackageProviderCore,
    KoDeclarationCastProviderCore {
    // We need to handle it this way because the import alias is the only type where the source declaration
    // is actually a reference to the imported declaration. In this case, returning `sourceDeclaration` would
    // not be accurate, so we return `this` to ensure the correct reference is maintained.
    override val koDeclarationCastProviderDeclaration: KoSourceDeclaration?
        get() =
            if (this is KoImportAliasDeclaration) {
                this
            } else {
                sourceDeclaration
            }
}
