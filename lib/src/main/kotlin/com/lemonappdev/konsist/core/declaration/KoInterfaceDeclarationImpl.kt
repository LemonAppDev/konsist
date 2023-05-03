package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtClass

internal class KoInterfaceDeclarationImpl private constructor(private val ktClass: KtClass) :
    KoComplexDeclarationImpl(ktClass),
    KoInterfaceDeclaration {
    override fun hasActualModifier() = hasModifiers(KoModifier.ACTUAL)

    override fun hasExpectModifier() = hasModifiers(KoModifier.EXPECT)

    internal companion object {
        private val cache = KoDeclarationCache<KoInterfaceDeclarationImpl>()

        internal fun getInstance(ktClass: KtClass) = cache.getOrCreateInstance(ktClass) { KoInterfaceDeclarationImpl(ktClass) }
    }
}
