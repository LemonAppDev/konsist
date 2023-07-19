package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoParentDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoDefaultValueProviderCore
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoRepresentsTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeProviderCore
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstance

internal class KoParameterDeclarationImpl private constructor(
    override val ktParameter: KtParameter,
    override val parentDeclaration: KoParentDeclarationProvider?,
) :
    KoParameterDeclaration,
    KoBaseDeclarationImpl(ktParameter),
    KoAnnotationProviderCore,
    KoPackageProviderCore,
    KoResideInOrOutsidePackageProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoDefaultValueProviderCore,
    KoModifierProviderCore,
    KoTopLevelProviderCore,
    KoTypeProviderCore,
    KoRepresentsTypeProviderCore {
    override val ktAnnotated: KtAnnotated
        get() = ktParameter

    override val ktFile: KtFile?
        get() = null

    override val ktTypeParameterListOwner: KtTypeParameterListOwner
        get() = ktParameter

    override val type: KoTypeDeclaration by lazy {
        val type = ktParameter
            .children
            .firstIsInstance<KtTypeReference>()

        KoTypeDeclarationImpl.getInstance(type, this)
    }

    override fun representsType(name: String): Boolean = type.name == name || type.fullyQualifiedName == name

    internal companion object {
        private val cache: KoDeclarationCache<KoParameterDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktParameter: KtParameter, parentDeclaration: KoParentDeclarationProvider?): KoParameterDeclaration =
            cache.getOrCreateInstance(ktParameter, parentDeclaration) { KoParameterDeclarationImpl(ktParameter, parentDeclaration) }
    }
}
