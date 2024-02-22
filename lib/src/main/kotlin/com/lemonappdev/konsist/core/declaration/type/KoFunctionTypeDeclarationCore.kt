package com.lemonappdev.konsist.core.declaration.type

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.KoParameterDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFunctionType
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtParameterList
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

internal class KoFunctionTypeDeclarationCore private constructor(
    private val ktFunctionType: KtFunctionType,
) :
    KoFunctionTypeDeclaration,
    KoTypeDeclarationCore,
    KoBaseProviderCore {
    override val psiElement: PsiElement by lazy { ktFunctionType }

    override val ktElement: KtElement by lazy { ktFunctionType }

    override val name: String by lazy { ktFunctionType.text }

    override val parameterTypes: List<KoParameterDeclaration> by lazy {
        ktFunctionType
            .children
            .filterIsInstance<KtParameterList>()
            .flatMap { it.children.toList() }
            .filterIsInstance<KtParameter>()
            .map { KoParameterDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }
    }

    override val returnType: KoTypeDeclaration by lazy {
        val types = ktFunctionType
            .children
            .filterIsInstance<KtTypeReference>()

        TypeUtil.getType(
            types,
            ktFunctionType.isExtensionDeclaration(),
            this.castToKoBaseDeclaration(),
            containingFile,
        ) ?: throw IllegalArgumentException("Lambda function has no specified type.")
    }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoFunctionTypeDeclaration> = KoDeclarationCache() // Todo: change this?

        internal fun getInstance(
            ktFunctionType: KtFunctionType,
            containingDeclaration: KoBaseDeclaration,
        ): KoFunctionTypeDeclaration =
            cache.getOrCreateInstance(ktFunctionType, containingDeclaration) {
                KoFunctionTypeDeclarationCore(
                    ktFunctionType,
                )
            }
    }
}
