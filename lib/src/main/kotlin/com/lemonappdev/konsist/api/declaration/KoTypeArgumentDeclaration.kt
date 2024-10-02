package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider

interface KoTypeArgumentDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoNameProvider {
    val typeArguments: List<KoTypeArgumentDeclaration>?

    val sourceDeclaration: KoTypeDeclaration
}
