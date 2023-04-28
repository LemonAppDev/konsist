package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.const.KoModifier
import org.jetbrains.kotlin.psi.KtClass

class KoInterfaceDeclaration private constructor(private val ktClass: KtClass) : KoComplexDeclaration(ktClass) {
    fun hasActualModifier() = hasModifiers(KoModifier.ACTUAL)

    fun hasExpectModifier() = hasModifiers(KoModifier.EXPECT)

    companion object {
        private val cache = KoDeclarationCache<KoInterfaceDeclaration>()

        fun getInstance(ktClass: KtClass) = cache.getOrCreateInstance(ktClass) { KoInterfaceDeclaration(ktClass) }
    }
}
