package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtConstructor
import org.jetbrains.kotlin.psi.KtPrimaryConstructor

internal class KoPrimaryConstructorDeclarationCore private constructor(
    private val ktPrimaryConstructor: KtPrimaryConstructor,
    override val containingDeclaration: KoBaseDeclaration,
) :
    KoPrimaryConstructorDeclaration,
    KoConstructorDeclarationCore {
    override val ktConstructor: KtConstructor<*> by lazy { ktPrimaryConstructor }

    override fun toString(): String = locationWithText

    internal companion object {
        private val cache: KoDeclarationCache<KoPrimaryConstructorDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktPrimaryConstructor: KtPrimaryConstructor,
            containingDeclaration: KoBaseDeclaration,
        ): KoPrimaryConstructorDeclaration =
            cache.getOrCreateInstance(ktPrimaryConstructor, containingDeclaration) {
                KoPrimaryConstructorDeclarationCore(
                    ktPrimaryConstructor,
                    containingDeclaration,
                )
            }
    }
}
