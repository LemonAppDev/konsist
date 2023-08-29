package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalClassProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import org.jetbrains.kotlin.psi.KtAnonymousInitializer
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty

internal class KoInitBlockDeclarationCore private constructor(
    private val ktAnonymousInitializer: KtAnonymousInitializer,
    override val containingDeclaration: KoContainingDeclarationProvider,
) :
    KoInitBlockDeclaration,
    KoBaseProviderCore,
    KoLocalClassProviderCore,
    KoLocalDeclarationProviderCore,
    KoLocalFunctionProviderCore,
    KoContainingFileProviderCore,
    KoLocationProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoTextProviderCore {
    override val psiElement: PsiElement by lazy { ktAnonymousInitializer }

    override val ktElement: KtElement by lazy { ktAnonymousInitializer }

    private val localDeclarationsHelper: List<KoBaseDeclaration> by lazy {
        val declarations = ktAnonymousInitializer
            .body
            ?.children
            ?.filterIsInstance<KtDeclaration>()
            ?: emptyList()

        declarations
            .mapNotNull {
                if (it is KtClass && !it.isInterface()) {
                    KoClassDeclarationCore.getInstance(it, this)
                } else if (it is KtFunction) {
                    KoFunctionDeclarationCore.getInstance(it, this)
                } else if (it is KtProperty) {
                    KoPropertyDeclarationCore.getInstance(it, this)
                } else {
                    null
                }
            }
    }

    override val localDeclarations: List<KoBaseDeclaration> by lazy { localDeclarationsHelper }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoInitBlockDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktAnonymousInitializer: KtAnonymousInitializer,
            containingDeclaration: KoContainingDeclarationProvider,
        ): KoInitBlockDeclaration =
            cache.getOrCreateInstance(ktAnonymousInitializer, containingDeclaration) {
                KoInitBlockDeclarationCore(ktAnonymousInitializer, containingDeclaration)
            }
    }
}
