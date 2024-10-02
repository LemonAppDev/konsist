package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import org.jetbrains.kotlin.psi.KtElement

data class KoTypeArgumentDeclarationCore(
    override val name: String,
    override val typeArguments: List<KoTypeArgumentDeclaration>?,
    override val sourceDeclaration: KoTypeDeclaration,
) : KoTypeArgumentDeclaration,
    KoBaseProviderCore,
    KoNameProviderCore {
    override val ktElement: KtElement? by lazy { null }

    override fun toString(): String = name
}
