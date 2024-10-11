package com.lemonappdev.konsist.core.declaration.type

import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.core.declaration.KoSourceDeclarationCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageProviderCore

internal interface KoBaseTypeDeclarationCore :
    KoBaseTypeDeclaration,
    KoSourceDeclarationCore,
    KoBaseProviderCore,
    KoNameProviderCore,
    KoTextProviderCore,
    KoPackageProviderCore,
    KoResideInPackageProviderCore,
    KoTypeProviderCore,
    KoTypeDeclarationProviderCore {
    // We need to handle it this way because the import alias is the only type where the source declaration
    // is actually a reference to the imported declaration. In this case, returning `sourceDeclaration` would
    // not be accurate, so we return `this` to ensure the correct reference is maintained.
    private val baseDeclaration: KoSourceDeclaration
        get() =
            if (this is KoImportAliasDeclaration) {
                this
            } else {
                sourceDeclaration
            }

    override val koTypeDeclarationProviderDeclaration: KoSourceDeclaration
        get() = baseDeclaration

    override val koTypeProviderDeclaration: KoSourceDeclaration
        get() = baseDeclaration
}
