package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationCoreProviderUtil
import org.jetbrains.kotlin.psi.KtAnonymousInitializer

internal class KoInitBlockDeclarationImpl private constructor(
    private val ktAnonymousInitializer: KtAnonymousInitializer,
    parentDeclaration: KoParentProvider?,
) :
    KoInitBlockDeclaration,
    KoBaseDeclarationImpl(ktAnonymousInitializer) {
    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoBaseDeclaration> = KoDeclarationCoreProviderUtil
        .getKoDeclarations(ktAnonymousInitializer, includeNested, includeLocal, this)

    internal companion object {
        private val cache: KoDeclarationCache<KoInitBlockDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktAnonymousInitializer: KtAnonymousInitializer,
            parentDeclaration: KoParentProvider?,
        ): KoInitBlockDeclaration =
            cache.getOrCreateInstance(ktAnonymousInitializer, parentDeclaration) {
                KoInitBlockDeclarationImpl(ktAnonymousInitializer, parentDeclaration)
            }
    }
}
