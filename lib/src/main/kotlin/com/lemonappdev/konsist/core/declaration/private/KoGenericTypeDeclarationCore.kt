package com.lemonappdev.konsist.core.declaration.private

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.type.KoBaseTypeDeclarationCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeArgumentProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtUserType

internal class KoGenericTypeDeclarationCore private constructor(
    override val ktUserType: KtUserType,
    override val containingDeclaration: KoBaseDeclaration,
) : KoBaseTypeDeclarationCore,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore,
    KoLocationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoTypeArgumentProviderCore,
    KoSourceDeclarationProviderCore {
    override val psiElement: PsiElement by lazy { ktUserType }

    override val ktElement: KtElement by lazy { ktUserType }

    override val ktTypeProjection: KtTypeProjection? by lazy { null }

    override val name: String by lazy { ktUserType.text }

    override val packagee: KoPackageDeclaration? by lazy { containingFile.packagee }

    override val sourceDeclaration: KoDeclarationCastProvider? by lazy {
        containingDeclaration as? KoDeclarationCastProvider
    }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoGenericTypeDeclarationCore> = KoDeclarationCache()

        internal fun getInstance(
            ktUserType: KtUserType,
            containingDeclaration: KoBaseDeclaration,
        ): KoGenericTypeDeclarationCore =
            cache.getOrCreateInstance(ktUserType, containingDeclaration) {
                KoGenericTypeDeclarationCore(ktUserType, containingDeclaration)
            }
    }
}
