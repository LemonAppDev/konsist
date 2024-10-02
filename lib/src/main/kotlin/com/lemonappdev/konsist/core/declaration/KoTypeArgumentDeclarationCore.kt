package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

data class KoTypeArgumentDeclarationCore(
    override val name: String,
    override val typeArguments: List<KoTypeArgumentDeclaration>?,
    override val sourceDeclaration: KoTypeDeclaration,
) : KoTypeArgumentDeclaration,
    KoBaseProviderCore, {
    override fun toString(): String = name
}
