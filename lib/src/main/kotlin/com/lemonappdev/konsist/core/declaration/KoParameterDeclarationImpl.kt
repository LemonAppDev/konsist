package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoDefaultValueProviderCore
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoRepresentsTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeProviderCore
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstance

internal class KoParameterDeclarationImpl private constructor(
    override val ktParameter: KtParameter,
    override val parentDeclaration: KoParentProvider?,
) :
    KoParameterDeclaration,
    KoBaseDeclarationImpl(ktParameter),
    KoAnnotationDeclarationProviderCore,
    KoPackageDeclarationProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoDefaultValueProviderCore,
    KoModifierProviderCore,
    KoTopLevelProviderCore,
    KoTypeProviderCore,
    KoRepresentsTypeProviderCore {
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

        internal fun getInstance(ktParameter: KtParameter, parentDeclaration: KoParentProvider?): KoParameterDeclaration =
            cache.getOrCreateInstance(ktParameter, parentDeclaration) { KoParameterDeclarationImpl(ktParameter, parentDeclaration) }
    }
}
