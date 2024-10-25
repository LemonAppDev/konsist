package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.combined.KoClassAndInterfaceDeclarationCore
import com.lemonappdev.konsist.core.declaration.combined.KoInterfaceAndObjectDeclarationCore
import com.lemonappdev.konsist.core.provider.KoTypeParameterProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoFunModifierProviderCore
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoInterfaceDeclarationCore private constructor(
    private val ktClass: KtClass,
    override val containingDeclaration: KoBaseDeclaration,
) : KoInterfaceDeclaration,
    KoClassAndInterfaceDeclarationCore,
    KoInterfaceAndObjectDeclarationCore,
    KoFunModifierProviderCore,
    KoTypeParameterProviderCore {
    override val ktAnnotationEntries: List<KtAnnotationEntry>? by lazy { ktClass.annotationEntries }

    override val ktModifierListOwner: KtModifierListOwner by lazy { ktClass }

    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktClass }

    override val psiElement: PsiElement by lazy { ktClass }

    override val ktElement: KtElement by lazy { ktClass }

    override val ktClassOrObject: KtClassOrObject by lazy { ktClass }

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoBaseDeclaration> =
        KoDeclarationProviderCoreUtil
            .getKoDeclarations(ktClass, includeNested, includeLocal, this)

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoInterfaceDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktClass: KtClass,
            containingDeclaration: KoBaseDeclaration,
        ): KoInterfaceDeclaration =
            cache.getOrCreateInstance(ktClass, containingDeclaration) {
                KoInterfaceDeclarationCore(ktClass, containingDeclaration)
            }
    }
}
