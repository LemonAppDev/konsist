package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.api.KoModifier
import org.jetbrains.kotlin.psi.KtClass

class KoInterfaceDeclarationImpl private constructor(private val ktClass: KtClass) : KoComplexDeclarationImpl(ktClass) {
    fun hasActualModifier() = hasModifiers(KoModifier.ACTUAL)

    fun hasExpectModifier() = hasModifiers(KoModifier.EXPECT)

    companion object {
        private val cache = KoDeclarationCache<KoInterfaceDeclarationImpl>()

        fun getInstance(ktClass: KtClass) = cache.getOrCreateInstance(ktClass) { KoInterfaceDeclarationImpl(ktClass) }
    }
}
