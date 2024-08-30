package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
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
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.psi.KtObjectDeclaration
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoObjectDeclarationCore(
    private val ktObjectDeclaration: KtObjectDeclaration,
    override val containingDeclaration: KoBaseDeclaration,
) : KoObjectDeclaration,
    KoClassAndObjectDeclarationCore,
    KoInterfaceAndObjectDeclarationCore,
    KoCompanionModifierProviderCore,
    KoDataModifierProviderCore,
    KoInitBlockProviderCore,
    /*
    We need to manually add KoNameProviderCore, even though KoObjectDeclarationCore indirectly implements this provider,
    because it is used to override the `name` property.
     */
    KoNameProviderCore,
    KoParentClassProviderCore {
    override val ktAnnotated: KtAnnotated by lazy { ktObjectDeclaration }

    override val ktModifierListOwner: KtModifierListOwner by lazy { ktObjectDeclaration }

    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktObjectDeclaration }

    override val psiElement: PsiElement by lazy { ktObjectDeclaration }

    override val ktElement: KtElement by lazy { ktObjectDeclaration }

    override val ktClassOrObject: KtClassOrObject by lazy { ktObjectDeclaration }

    override val name: String by lazy {
        if (hasCompanionModifier && super<KoNameProviderCore>.name == "") {
            "Companion"
        } else {
            super<KoNameProviderCore>.name
        }
    }

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoBaseDeclaration> =
        KoDeclarationProviderCoreUtil
            .getKoDeclarations(ktObjectDeclaration, includeNested, includeLocal, this)

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoObjectDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktObjectDeclaration: KtObjectDeclaration,
            containingDeclaration: KoBaseDeclaration,
        ): KoObjectDeclaration =
            cache.getOrCreateInstance(ktObjectDeclaration, containingDeclaration) {
                KoObjectDeclarationCore(
                    ktObjectDeclaration,
                    containingDeclaration,
                )
            }
    }
}
