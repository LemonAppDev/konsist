package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

data class KoTypeArgumentDeclarationCore(
    override val name: String,
    override val typeArgument: KoTypeArgumentDeclaration?,
    override val sourceDeclaration: KoBaseTypeDeclaration,
) : KoTypeArgumentDeclaration,
    KoBaseProviderCore
