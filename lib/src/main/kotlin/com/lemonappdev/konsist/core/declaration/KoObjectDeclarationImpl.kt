package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtObjectDeclaration

internal class KoObjectDeclarationImpl(
    ktObjectDeclaration: KtObjectDeclaration,
    parentDeclaration: KoBaseDeclaration?,
) :
    KoComplexDeclarationImpl(ktObjectDeclaration, parentDeclaration),
    KoObjectDeclaration {

    override val name: String by lazy {
        if (hasCompanionModifier() && super.name == "") {
            "Companion"
        } else {
            super.name
        }
    }

    override fun hasDataModifier() = hasModifiers(KoModifier.DATA)

    override fun hasCompanionModifier() = hasModifiers(KoModifier.COMPANION)

    internal companion object {
        private val cache = KoDeclarationCache<KoObjectDeclarationImpl>()

        internal fun getInstance(ktObjectDeclaration: KtObjectDeclaration, parentDeclaration: KoBaseDeclaration?) =
            cache.getOrCreateInstance(ktObjectDeclaration, parentDeclaration) { KoObjectDeclarationImpl(ktObjectDeclaration, parentDeclaration) }
    }
}
