package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.core.cache.KoPropertyCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoFunctionTypeDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoIsFunctionTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoIsGenericProviderCore
import com.lemonappdev.konsist.core.provider.KoIsStarProjectionProviderCore
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
import org.jetbrains.kotlin.psi.KtNullableType
import org.jetbrains.kotlin.psi.KtTypeElement
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType

data class KoTypeArgumentDeclarationCore(
    override val name: String,
    override val sourceDeclaration: KoDeclarationCastProvider?,
    override val typeArguments: List<KoTypeArgumentDeclaration>,
    override val hasInModifier: Boolean,
    override val hasOutModifier: Boolean,
    override val isStarProjection: Boolean,
    override val ktTypeProjection: KtTypeProjection,
    // Declaration containing this type argument (e.g. used to check if the type argument is suppressed by the parent)
    internal val parentDeclaration: KoBaseDeclaration,
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
    KoIsGenericProviderCore,
    KoIsFunctionTypeProviderCore,
    KoFunctionTypeDeclarationProviderCore,
    KoIsStarProjectionProviderCore {
    override val ktElement: KtElement
        get() = ktTypeProjection

    // Nested type arguments are passed via the constructor, the user type is only used to detect generic types
    override val ktUserType: KtUserType?
        get() = nonNullableTypeElement as? KtUserType

    override val psiElement: PsiElement
        get() = ktTypeProjection

    override val ktModifierListOwner: KtModifierListOwner
        get() = ktTypeProjection

    private val nonNullableTypeElement: KtTypeElement?
        get() =
            when (val typeElement = ktTypeProjection.typeReference?.typeElement) {
                is KtNullableType -> typeElement.innerType
                else -> typeElement
            }

    override val ktFunctionType: KtFunctionType?
        get() = nonNullableTypeElement as? KtFunctionType

    override val ktNameReferenceExpression: KtNameReferenceExpression?
        get() = null

    override val ktTypeReference: KtTypeReference?
        get() = null

    @Volatile
    private var cachedText: Any? = KoPropertyCache.NOT_COMPUTED

    override val text: String
        get() =
            KoPropertyCache.getOrCompute(cachedText, { cachedText = it }) {
                when {
                    hasInModifier -> "in $name"
                    hasOutModifier -> "out $name"
                    else -> name
                }
            }

    @Volatile
    private var cachedModifiers: Any? = KoPropertyCache.NOT_COMPUTED

    override val modifiers: List<KoModifier>
        get() =
            KoPropertyCache.getOrCompute(cachedModifiers, { cachedModifiers = it }) {
                when {
                    hasInModifier -> listOf(KoModifier.IN)
                    hasOutModifier -> listOf(KoModifier.OUT)
                    else -> emptyList()
                }
            }

    override fun toString(): String = text
}
