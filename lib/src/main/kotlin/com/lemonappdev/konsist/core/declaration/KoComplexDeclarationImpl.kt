package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration
import com.lemonappdev.konsist.api.declaration.KoNamedDeclaration
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationCoreProviderUtil
import org.jetbrains.kotlin.psi.KtClassOrObject

internal abstract class KoComplexDeclarationImpl(
    private val ktClassOrObject: KtClassOrObject,
    parentDeclaration: KoBaseDeclaration?,
) : KoDeclarationImpl(ktClassOrObject, parentDeclaration),
    KoComplexDeclaration {

    override fun representsType(name: String): Boolean = name == this.name || name == fullyQualifiedName

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoNamedDeclaration> = KoDeclarationCoreProviderUtil
        .getKoDeclarations(ktClassOrObject, includeNested, includeLocal, this)
}
