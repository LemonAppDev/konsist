package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationProviderUtil
import org.jetbrains.kotlin.psi.KtClassOrObject

internal abstract class KoComplexDeclarationImpl(
    private val ktClassOrObject: KtClassOrObject,
    parent: KoBaseDeclaration,
) : KoDeclarationImpl(ktClassOrObject, parent),
    KoComplexDeclaration {

    override fun representsType(name: String) = name == this.name || name == fullyQualifiedName

    override fun declarations(
        modifiers: List<KoModifier>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoDeclarationImpl> = KoDeclarationProviderUtil
        .getKoDeclarations(ktClassOrObject, modifiers, includeNested, includeLocal, this)
}
