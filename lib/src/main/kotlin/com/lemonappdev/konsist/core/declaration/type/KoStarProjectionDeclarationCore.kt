package com.lemonappdev.konsist.core.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.KoSourceDeclarationCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtTypeProjection

internal class KoStarProjectionDeclarationCore private constructor(
    override val ktTypeProjection: KtTypeProjection,
    override val containingDeclaration: KoBaseDeclaration,
) : KoStarProjectionDeclaration,
    KoSourceDeclarationCore,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore,
    KoLocationProviderCore,
    KoPathProviderCore {
    override val ktElement: KtElement by lazy { ktTypeProjection }

    override val psiElement: PsiElement by lazy { ktTypeProjection }

    override val koDeclarationCastProviderDeclaration: KoSourceDeclaration? by lazy { sourceDeclaration }

    override val name: String by lazy { text }

    override val text: String by lazy { "*" }

    override fun toString(): String = text

    internal companion object {
        private val cache: KoDeclarationCache<KoStarProjectionDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktTypeProjection: KtTypeProjection,
            containingDeclaration: KoBaseDeclaration,
        ): KoStarProjectionDeclaration =
            cache.getOrCreateInstance(ktTypeProjection, containingDeclaration) {
                KoStarProjectionDeclarationCore(ktTypeProjection, containingDeclaration)
            }
    }
}
