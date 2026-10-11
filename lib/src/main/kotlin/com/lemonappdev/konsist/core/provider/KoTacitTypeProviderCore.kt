package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoNullableTypeProvider
import com.lemonappdev.konsist.api.provider.KoTacitTypeProvider
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtParenthesizedExpression
import kotlin.reflect.KClass

internal interface KoTacitTypeProviderCore :
    KoTacitTypeProvider,
    KoBaseProviderCore,
    KoNullableTypeProvider,
    KoValueProviderCore {
    override val tacitType: KoTypeDeclaration?
        get() =
            type ?: getConstructorCallee(ktExpression)?.let {
                KoTypeDeclarationCore.getInstance(it, this.castToKoBaseDeclaration())
            }

    override fun hasTacitType(type: String): Boolean = tacitType?.let { it.name == type || it.name.substringBefore("<") == type } ?: false

    override fun hasTacitTypeOf(kClass: KClass<*>): Boolean = TypeUtil.hasTypeOf(tacitType, kClass)
}

// Functions returning their receiver, so `Foo().apply { }` has the type `Foo`
private val receiverReturningFunctions = setOf("apply", "also")

private val qualifierRegex = Regex("""\w+(\.\w+)*""")

/*
 * Returns the name of the constructor called by the expression, e.g. `Foo` for `Foo()`, `Foo<Bar>()`,
 * `com.app.Foo()` or `Foo().apply { }`. Without type resolution a constructor call is recognized
 * by the name starting with an uppercase letter.
 */
private fun getConstructorCallee(expression: KtExpression?): KtNameReferenceExpression? =
    when (expression) {
        is KtParenthesizedExpression -> {
            getConstructorCallee(expression.expression)
        }

        is KtCallExpression -> {
            (expression.calleeExpression as? KtNameReferenceExpression)
                ?.takeIf { it.getReferencedName().firstOrNull()?.isUpperCase() == true }
        }

        is KtDotQualifiedExpression -> {
            val selector = expression.selectorExpression as? KtCallExpression
            val selectorName = (selector?.calleeExpression as? KtNameReferenceExpression)?.getReferencedName()

            when {
                selectorName in receiverReturningFunctions -> getConstructorCallee(expression.receiverExpression)
                qualifierRegex.matches(expression.receiverExpression.text) -> getConstructorCallee(selector)
                else -> null
            }
        }

        else -> {
            null
        }
    }
