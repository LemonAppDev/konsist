package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider

interface KoTypeArgumentDeclaration :
    KoBaseDeclaration,
    KoBaseProvider {
    val name: String

    val typeArguments: List<KoTypeArgumentDeclaration>?

    val sourceDeclaration: KoTypeDeclaration
}
