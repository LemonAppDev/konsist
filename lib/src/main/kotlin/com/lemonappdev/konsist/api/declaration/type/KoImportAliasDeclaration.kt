package com.lemonappdev.konsist.api.declaration.type

import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoNonNullableTypeProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider

interface KoImportAliasDeclaration :
    KoBaseTypeDeclaration,
    KoNonNullableTypeProvider,
    KoContainingFileProvider,
    KoContainingDeclarationProvider,
    KoLocationProvider,
    KoPathProvider,
    KoModuleProvider,
    KoSourceSetProvider {
//    val importDirective: KoImportDeclaration
}
