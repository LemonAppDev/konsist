package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtClass

internal class KoInterfaceDeclarationImpl private constructor(ktClass: KtClass, parentDeclaration: KoBaseDeclaration?) :
    KoComplexDeclarationImpl(ktClass, parentDeclaration),
    KoInterfaceDeclaration {
    override fun hasActualModifier(): Boolean = hasModifiers(KoModifier.ACTUAL)

    override fun hasExpectModifier(): Boolean = hasModifiers(KoModifier.EXPECT)

    override fun hasFunModifier(): Boolean = hasModifiers(KoModifier.FUN)

    internal companion object {
        private val cache: KoDeclarationCache<KoInterfaceDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktClass: KtClass, parentDeclaration: KoBaseDeclaration?): KoInterfaceDeclaration =
            cache.getOrCreateInstance(ktClass, parentDeclaration) {
                KoInterfaceDeclarationImpl(ktClass, parentDeclaration)
            }
    }
}
