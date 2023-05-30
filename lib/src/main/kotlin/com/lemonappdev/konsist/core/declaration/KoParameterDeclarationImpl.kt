package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtConstantExpression
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstance
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

internal class KoParameterDeclarationImpl private constructor(private val ktParameter: KtParameter, parentDeclaration: KoBaseDeclaration?) :
    KoDeclarationImpl(ktParameter, parentDeclaration),
    KoParameterDeclaration {

    override val type: KoTypeDeclaration by lazy {
        val type = ktParameter
            .children
            .firstIsInstance<KtTypeReference>()

        KoTypeDeclarationImpl.getInstance(type, this)
    }

    override val defaultValue: String? by lazy {
        // eg. primitive value as default parameter value
        val constantExpressionText = ktParameter
            .children
            .firstIsInstanceOrNull<KtConstantExpression>()
            ?.text

        if (constantExpressionText != null) {
            return@lazy constantExpressionText
        }

        // eg. function call as default parameter value
        val callExpressionText = ktParameter
            .children
            .firstIsInstanceOrNull<KtCallExpression>()
            ?.text

        callExpressionText
    }

    override fun hasVarargModifier(): Boolean = hasModifiers(KoModifier.VARARG)

    override fun hasNoInlineModifier(): Boolean = hasModifiers(KoModifier.NOINLINE)

    override fun hasCrossInlineModifier(): Boolean = hasModifiers(KoModifier.CROSSINLINE)

    override fun hasDefaultValue(value: String?): Boolean = when (value) {
        null -> ktParameter.hasDefaultValue()
        else -> defaultValue == value
    }

    override fun representsType(name: String): Boolean = type.name == name || type.fullyQualifiedName == name

    internal companion object {
        private val cache: KoDeclarationCache<KoParameterDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktParameter: KtParameter, parentDeclaration: KoBaseDeclaration?): KoParameterDeclaration =
            cache.getOrCreateInstance(ktParameter, parentDeclaration) { KoParameterDeclarationImpl(ktParameter, parentDeclaration) }
    }
}
