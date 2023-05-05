package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtClass

internal class KoInterfaceDeclarationImpl private constructor(private val ktClass: KtClass, parent: KoBaseDeclarationImpl) :
    KoComplexDeclarationImpl(ktClass, parent),
    KoInterfaceDeclaration {
    override fun hasActualModifier() = hasModifiers(KoModifier.ACTUAL)

    override fun hasExpectModifier() = hasModifiers(KoModifier.EXPECT)

    internal companion object {
        private val cache = KoDeclarationCache<KoInterfaceDeclarationImpl>()

        internal fun getInstance(ktClass: KtClass, parent: KoBaseDeclarationImpl) = cache.getOrCreateInstance(ktClass, parent) {
            KoInterfaceDeclarationImpl(ktClass, parent)
        }
    }
}
