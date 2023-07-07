package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import org.jetbrains.kotlin.psi.KtClass

internal class KoInterfaceDeclarationImpl private constructor(ktClass: KtClass, parentDeclaration: KoParentProvider?) :
    KoComplexDeclarationImpl(ktClass, parentDeclaration),
    KoInterfaceDeclaration,
    KoModifierProviderCore {

    internal companion object {
        private val cache: KoDeclarationCache<KoInterfaceDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktClass: KtClass, parentDeclaration: KoParentProvider?): KoInterfaceDeclaration =
            cache.getOrCreateInstance(ktClass, parentDeclaration) {
                KoInterfaceDeclarationImpl(ktClass, parentDeclaration)
            }
    }
}
