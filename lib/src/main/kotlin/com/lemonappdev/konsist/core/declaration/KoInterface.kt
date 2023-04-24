package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.psiUtil.hasActualModifier
import org.jetbrains.kotlin.psi.psiUtil.hasExpectModifier

class KoInterface private constructor(private val ktClass: KtClass) : KoComplexDeclaration(ktClass) {

    fun hasActualModifier() = ktClass.hasActualModifier()

    fun hasExpectModifier() = ktClass.hasExpectModifier()

    companion object {
        private val cache = KoDeclarationCache<KoInterface>()

        fun getInstance(ktClass: KtClass) = cache.getOrCreateInstance(ktClass) { KoInterface(ktClass) }
    }
}
