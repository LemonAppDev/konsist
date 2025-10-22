package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoCompanionObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoHasDefaultNameProviderCore
import com.lemonappdev.konsist.core.util.CompanionUtil.COMPANION_NAME
import org.jetbrains.kotlin.psi.KtObjectDeclaration

internal class KoCompanionObjectDeclarationCore(
    ktObjectDeclaration: KtObjectDeclaration,
    override val containingDeclaration: KoBaseDeclaration,
) : KoObjectDeclarationCore(ktObjectDeclaration, containingDeclaration),
    KoCompanionObjectDeclaration,
    KoHasDefaultNameProviderCore {
    override val name: String by lazy {
        if (super<KoObjectDeclarationCore>.name == "") {
            COMPANION_NAME
        } else {
            super<KoObjectDeclarationCore>.name
        }
    }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoObjectDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktObjectDeclaration: KtObjectDeclaration,
            containingDeclaration: KoBaseDeclaration,
        ): KoObjectDeclaration =
            cache.getOrCreateInstance(ktObjectDeclaration, containingDeclaration) {
                KoCompanionObjectDeclarationCore(
                    ktObjectDeclaration,
                    containingDeclaration,
                )
            }
    }
}
