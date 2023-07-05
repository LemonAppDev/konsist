package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtPrimaryConstructor

internal class KoPrimaryConstructorDeclarationCore private constructor(
    ktPrimaryConstructor: KtPrimaryConstructor,
    parentDeclaration: KoBaseDeclaration?,
) :
    KoConstructorDeclarationImpl(ktPrimaryConstructor, parentDeclaration), KoPrimaryConstructorDeclaration {
    internal companion object {
        private val cache: KoDeclarationCache<KoPrimaryConstructorDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktPrimaryConstructor: KtPrimaryConstructor,
            parentDeclaration: KoBaseDeclaration?,
        ): KoPrimaryConstructorDeclaration =
            cache.getOrCreateInstance(ktPrimaryConstructor, parentDeclaration) {
                KoPrimaryConstructorDeclarationCore(
                    ktPrimaryConstructor,
                    parentDeclaration,
                )
            }
    }
}
