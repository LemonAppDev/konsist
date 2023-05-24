package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtClass

internal class KoInterfaceDeclarationImpl private constructor(ktClass: KtClass, parentDeclaration: KoBaseDeclaration?) :
    KoComplexDeclarationImpl(ktClass, parentDeclaration),
    KoInterfaceDeclaration {
    override fun hasActualModifier() = hasModifiers(KoModifier.ACTUAL)

    override fun hasExpectModifier() = hasModifiers(KoModifier.EXPECT)

    internal companion object {
        private val cache = KoDeclarationCache<KoInterfaceDeclarationImpl>()

        internal fun getInstance(ktClass: KtClass, parentDeclaration: KoBaseDeclaration?) =
            cache.getOrCreateInstance(ktClass, parentDeclaration) {
                KoInterfaceDeclarationImpl(ktClass, parentDeclaration)
            }
    }
}
