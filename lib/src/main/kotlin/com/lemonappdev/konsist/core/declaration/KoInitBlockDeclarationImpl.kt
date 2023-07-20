package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration
import com.lemonappdev.konsist.api.provider.KoParentDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoClassProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoPropertyProviderCore
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil
import org.jetbrains.kotlin.psi.KtAnonymousInitializer

internal class KoInitBlockDeclarationImpl private constructor(
    private val ktAnonymousInitializer: KtAnonymousInitializer,
    override val parentDeclaration: KoParentDeclarationProvider?,
) :
    KoInitBlockDeclaration,
    KoDeclarationProviderCore,
    KoClassProviderCore,
    KoPropertyProviderCore,
    KoFunctionProviderCore,
    KoBaseDeclarationImpl(ktAnonymousInitializer) {
    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoBaseDeclaration> = KoDeclarationProviderCoreUtil
        .getKoDeclarations(ktAnonymousInitializer, includeNested, includeLocal, this)

    internal companion object {
        private val cache: KoDeclarationCache<KoInitBlockDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktAnonymousInitializer: KtAnonymousInitializer,
            parentDeclaration: KoParentDeclarationProvider?,
        ): KoInitBlockDeclaration =
            cache.getOrCreateInstance(ktAnonymousInitializer, parentDeclaration) {
                KoInitBlockDeclarationImpl(ktAnonymousInitializer, parentDeclaration)
            }
    }
}
