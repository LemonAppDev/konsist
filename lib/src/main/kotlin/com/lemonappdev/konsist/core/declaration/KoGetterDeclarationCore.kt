package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoGetterDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoBodyProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoVisibilityModifierProviderCore
import org.jetbrains.kotlin.psi.KtDeclarationWithBody
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.psi.KtPropertyAccessor
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.isProtected
import org.jetbrains.kotlin.psi.psiUtil.isPublic

internal class KoGetterDeclarationCore private constructor(
    private val ktPropertyAccessor: KtPropertyAccessor,
    override val containingDeclaration: KoContainingDeclarationProvider,
) :
    KoGetterDeclaration,
    KoBaseProviderCore,
    KoBodyProviderCore,
    KoContainingDeclarationProviderCore,
    KoContainingFileProviderCore,
    KoLocationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoTextProviderCore,
    KoVisibilityModifierProviderCore {
    override val ktElement: KtElement by lazy { ktPropertyAccessor }

    override val psiElement: PsiElement by lazy { ktPropertyAccessor }

    override val ktDeclarationWithBody: KtDeclarationWithBody by lazy { ktPropertyAccessor }

    override val ktModifierListOwner: KtModifierListOwner by lazy { ktPropertyAccessor }

    override fun toString(): String = locationWithText

    internal companion object {
        private val cache: KoDeclarationCache<KoGetterDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktPropertyAccessor: KtPropertyAccessor,
            containingDeclaration: KoContainingDeclarationProvider,
        ): KoGetterDeclaration =
            cache.getOrCreateInstance(ktPropertyAccessor, containingDeclaration) {
                KoGetterDeclarationCore(ktPropertyAccessor, containingDeclaration)
            }
    }
}
