package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.type.KoBaseTypeDeclarationCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoValueProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtTypeParameter

internal class KoTypeParameterDeclarationCore private constructor(
    private val ktTypeParameter: KtTypeParameter,
    override val containingDeclaration: KoBaseDeclaration,
) : KoTypeParameterDeclaration,
    KoBaseTypeDeclarationCore,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore,
    KoLocationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore  {
    override val psiElement: PsiElement by lazy { ktTypeParameter }

    override val ktElement: KtElement by lazy { ktTypeParameter }

    override val packagee: KoPackageDeclaration? by lazy { containingFile.packagee }

    override val name: String by lazy { ktTypeParameter.text }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoTypeParameterDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktTypeParameter: KtTypeParameter,
            containingDeclaration: KoBaseDeclaration,
        ): KoTypeParameterDeclaration =
            cache.getOrCreateInstance(ktTypeParameter, containingDeclaration) {
                KoTypeParameterDeclarationCore(ktTypeParameter, containingDeclaration)
            }
    }
}
