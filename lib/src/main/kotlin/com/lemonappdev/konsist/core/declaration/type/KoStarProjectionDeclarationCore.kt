package com.lemonappdev.konsist.core.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtTypeProjection

internal class KoStarProjectionDeclarationCore private constructor(
    private val ktTypeProjection: KtTypeProjection,
    override val containingDeclaration: KoBaseDeclaration,
) : KoStarProjectionDeclaration,
    KoBaseTypeDeclarationCore,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore,
    KoLocationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore {
    override val psiElement: PsiElement by lazy { ktTypeProjection }

    override val ktElement: KtElement by lazy { ktTypeProjection }

    override val name: String by lazy { ktTypeProjection.text }

    override val packagee: KoPackageDeclaration? by lazy { containingFile.packagee }

    override fun toString(): String = name

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