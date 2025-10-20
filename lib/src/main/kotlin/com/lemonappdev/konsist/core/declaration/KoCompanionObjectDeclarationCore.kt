package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoCompanionObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.provider.modifier.KoCompanionModifierProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.combined.KoClassAndObjectDeclarationCore
import com.lemonappdev.konsist.core.declaration.combined.KoInterfaceAndObjectDeclarationCore
import com.lemonappdev.konsist.core.provider.KoInitBlockProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoParentClassProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoCompanionModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoDataModifierProviderCore
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.psi.KtObjectDeclaration
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoCompanionObjectDeclarationCore(
    ktObjectDeclaration: KtObjectDeclaration,
    override val containingDeclaration: KoBaseDeclaration,
) : KoCompanionObjectDeclaration,
    KoObjectDeclarationCore(ktObjectDeclaration, containingDeclaration) {
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
