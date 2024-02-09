package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalParentDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalTypeDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.api.declaration.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.cache.KoExternalParentCache
import com.lemonappdev.konsist.core.cache.KoExternalTypeCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry
import org.jetbrains.kotlin.psi.KtTypeReference

internal class KoFunctionTypeDeclarationCore private constructor(
    private val ktTypeReference: KtTypeReference,
) :
    KoFunctionTypeDeclaration,
    KoTypeDeclarationCore,
    KoBaseProviderCore {
    override val psiElement: PsiElement by lazy { ktTypeReference }

    override val ktElement: KtElement by lazy { ktTypeReference }

    override val name: String by lazy { ktTypeReference.text }

    override val parameterTypes: List<KoTypeDeclaration>
        get() = TODO("Not yet implemented")

    override val returnType: KoTypeDeclaration
        get() = TODO("Not yet implemented")

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoFunctionTypeDeclaration> = KoDeclarationCache() // Todo: change this?

        internal fun getInstance(
            ktTypeReference: KtTypeReference,
            containingDeclaration: KoBaseDeclaration,
        ): KoFunctionTypeDeclaration =
            cache.getOrCreateInstance(ktTypeReference, containingDeclaration) {
                KoFunctionTypeDeclarationCore(
                    ktTypeReference,
                )
            }
    }
}
