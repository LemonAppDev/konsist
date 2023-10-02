package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoGetterDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import org.jetbrains.kotlin.psi.KtPropertyAccessor

internal class KoGetterDeclarationCore private constructor(
     private val ktPropertyAccessor: KtPropertyAccessor,
     override val containingDeclaration: KoContainingDeclarationProvider,
) :
    KoGetterDeclaration,
    KoBaseProviderCore,
    KoContainingDeclarationProviderCore {

    override fun toString(): String = TODO() // locationWithText

    internal companion object {
        private val cache: KoDeclarationCache<KoGetterDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktPropertyAccessor: KtPropertyAccessor,
            containingDeclaration: KoContainingDeclarationProvider,
        ): KoGetterDeclaration =
            cache.getOrCreateInstance(ktPropertyAccessor, containingDeclaration) {
                KoGetterDeclarationCore(ktPropertyAccessor, containingDeclaration)
            }
    }
}
