package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoGenericTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoStarProjectionProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeArgumentProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoTypeProjectionModifierProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.psi.KtUserType

data class KoTypeArgumentDeclarationCore(
    override val name: String,
    override val genericType: KoSourceDeclaration,
    override val typeArguments: List<KoTypeArgumentDeclaration>?,
    override val sourceDeclaration: KoSourceDeclaration,
    override val isStarProjection: Boolean,
    override val hasInModifier: Boolean,
    override val hasOutModifier: Boolean,
) : KoTypeArgumentDeclaration,
    KoBaseProviderCore,
    KoNameProviderCore,
    KoTextProviderCore,
    KoSourceDeclarationProviderCore,
    KoTypeArgumentProviderCore,
    KoGenericTypeProviderCore,
    KoStarProjectionProviderCore,
    KoModifierProviderCore,
    KoTypeProjectionModifierProviderCore {
    override val ktElement: KtElement? by lazy { null }

    override val ktUserType: KtUserType? by lazy { null }

    override val psiElement: PsiElement? by lazy { null }

    override val ktModifierListOwner: KtModifierListOwner? by lazy { null }

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
