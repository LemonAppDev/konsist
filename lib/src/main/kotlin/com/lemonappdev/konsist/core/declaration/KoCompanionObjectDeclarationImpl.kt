package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoCompanionObjectDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtObjectDeclaration

internal class KoCompanionObjectDeclarationImpl private constructor(
    ktObjectDeclaration: KtObjectDeclaration,
    parent: KoBaseDeclaration,
) :
    KoComplexDeclarationImpl(ktObjectDeclaration, parent), KoCompanionObjectDeclaration {
    override fun hasName() = name != DEFAULT_COMPANION_OBJECT_NAME

    internal companion object {
        private val cache = KoDeclarationCache<KoCompanionObjectDeclarationImpl>()

        internal const val DEFAULT_COMPANION_OBJECT_NAME = "Companion"

        internal fun getInstance(ktObjectDeclaration: KtObjectDeclaration, parent: KoBaseDeclaration) =
            cache.getOrCreateInstance(ktObjectDeclaration, parent) { KoCompanionObjectDeclarationImpl(ktObjectDeclaration, parent) }
    }
}
