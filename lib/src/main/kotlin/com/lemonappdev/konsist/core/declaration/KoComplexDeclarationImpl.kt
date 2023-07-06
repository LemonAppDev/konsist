package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationCoreProviderUtil
import com.lemonappdev.konsist.core.provider.KoAnnotationDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal abstract class KoComplexDeclarationImpl(
    private val ktClassOrObject: KtClassOrObject,
    parentDeclaration: KoParentProvider?,
) : KoComplexDeclaration,
    KoBaseDeclarationImpl(ktClassOrObject),
    KoAnnotationDeclarationProviderCore,
    KoPackageDeclarationProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoModifierProviderCore,
    KoTopLevelProviderCore {
    override val ktTypeParameterListOwner: KtTypeParameterListOwner
        get() = ktClassOrObject

    override fun representsType(name: String): Boolean = name == this.name || name == fullyQualifiedName

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoBaseDeclaration> = KoDeclarationCoreProviderUtil
        .getKoDeclarations(ktClassOrObject, includeNested, includeLocal, this)
}
