package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoGenericTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeArgumentProviderCore
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtUserType

data class KoTypeArgumentDeclarationCore(
    override val name: String,
    override val genericType: KoBaseTypeDeclaration,
    override val typeArguments: List<KoTypeArgumentDeclaration>?,
    override val sourceDeclaration: KoBaseTypeDeclaration,
) : KoTypeArgumentDeclaration,
    KoBaseProviderCore,
    KoNameProviderCore,
    KoSourceDeclarationProviderCore,
    KoTypeArgumentProviderCore,
    KoGenericTypeProviderCore {
    override val ktElement: KtElement? by lazy { null }

    override val ktUserType: KtUserType? by lazy { null }

    override fun toString(): String = name
}
