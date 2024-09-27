package com.lemonappdev.konsist.core.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoFunctionTypeDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFunctionType

internal class KoFunctionTypeDeclarationCore private constructor(
    override val ktFunctionType: KtFunctionType,
    override val containingDeclaration: KoBaseDeclaration,
) : KoFunctionTypeDeclaration,
    KoBaseTypeDeclarationCore,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore,
    KoLocationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoFunctionTypeDeclarationProviderCore {
    override val psiElement: PsiElement by lazy { ktFunctionType }

    override val ktElement: KtElement by lazy { ktFunctionType }

    override val name: String by lazy { ktFunctionType.text }

    override val packagee: KoPackageDeclaration? by lazy { containingFile.packagee }

    override fun toString(): String = text

    internal companion object {
        private val cache: KoDeclarationCache<KoFunctionTypeDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktFunctionType: KtFunctionType,
            containingDeclaration: KoBaseDeclaration,
        ): KoFunctionTypeDeclaration =
            cache.getOrCreateInstance(ktFunctionType, containingDeclaration) {
                KoFunctionTypeDeclarationCore(ktFunctionType, containingDeclaration)
            }
    }
}
