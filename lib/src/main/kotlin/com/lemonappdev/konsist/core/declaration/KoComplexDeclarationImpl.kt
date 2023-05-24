package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration
import com.lemonappdev.konsist.api.declaration.KoNamedDeclaration
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationCoreProviderUtil
import com.lemonappdev.konsist.core.parent.KoParent
import org.jetbrains.kotlin.psi.KtClassOrObject

internal abstract class KoComplexDeclarationImpl(
    private val ktClassOrObject: KtClassOrObject,
    parent: KoParent,
) : KoDeclarationImpl(ktClassOrObject, parent),
    KoComplexDeclaration {

    override fun representsType(name: String) = name == this.name || name == fullyQualifiedName

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoNamedDeclaration> = KoDeclarationCoreProviderUtil
        .getKoDeclarations(ktClassOrObject, includeNested, includeLocal, this)
}
