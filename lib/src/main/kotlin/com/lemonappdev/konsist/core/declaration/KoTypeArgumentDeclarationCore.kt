package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.provider.KoIsGenericProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationCastProviderCore
import com.lemonappdev.konsist.core.provider.KoFunctionTypeDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoIsFunctionTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoIsGenericProviderCore
import com.lemonappdev.konsist.core.provider.KoIsGenericTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeArgumentProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoInModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoOutModifierProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFunctionType
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType

data class KoTypeArgumentDeclarationCore(
    override val name: String,
    override val sourceDeclaration: KoSourceDeclaration?,
    override val typeArguments: List<KoTypeArgumentDeclaration>?,
    override val hasInModifier: Boolean,
    override val hasOutModifier: Boolean,
    override val ktTypeProjection: KtTypeProjection,
) : KoTypeArgumentDeclaration,
    KoBaseProviderCore,
    KoNameProviderCore,
    KoTextProviderCore,
    KoSourceDeclarationProviderCore,
    KoTypeArgumentProviderCore,
    KoModifierProviderCore,
    KoOutModifierProviderCore,
    KoInModifierProviderCore,
    KoLocationProviderCore,
    KoPathProviderCore,
    KoDeclarationCastProviderCore,
    KoIsGenericTypeProviderCore,
    KoIsGenericProviderCore,
    KoIsFunctionTypeProviderCore,
    KoFunctionTypeDeclarationProviderCore {
    override val ktElement: KtElement by lazy { ktTypeProjection }

    override val ktUserType: KtUserType? by lazy { null }

    override val psiElement: PsiElement by lazy { ktTypeProjection }

    override val ktModifierListOwner: KtModifierListOwner by lazy { ktTypeProjection }

    override val ktFunctionType: KtFunctionType? by lazy {
        ktTypeProjection
            .children
            .firstOrNull()
            ?.children
            ?.filterIsInstance<KtFunctionType>()
            ?.firstOrNull()
    }

    override val ktNameReferenceExpression: KtNameReferenceExpression? by lazy { null }

    override val ktTypeReference: KtTypeReference? by lazy { null }

    override val koDeclarationCastProviderDeclaration: KoSourceDeclaration? by lazy { sourceDeclaration }

    override val text: String by lazy {
        when {
            hasInModifier -> "in $name"
            hasOutModifier -> "out $name"
            else -> name
        }
    }

    override val modifiers: List<KoModifier> by lazy {
        when {
            hasInModifier -> listOf(KoModifier.IN)
            hasOutModifier -> listOf(KoModifier.OUT)
            else -> emptyList()
        }
    }

    override fun toString(): String = text
}
