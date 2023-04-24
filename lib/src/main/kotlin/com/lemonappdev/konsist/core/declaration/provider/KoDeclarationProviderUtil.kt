package com.lemonappdev.konsist.core.declaration.provider

import com.lemonappdev.konsist.core.const.KoModifier
import com.lemonappdev.konsist.core.declaration.KoClass
import com.lemonappdev.konsist.core.declaration.KoCompanionObject
import com.lemonappdev.konsist.core.declaration.KoComplexDeclaration
import com.lemonappdev.konsist.core.declaration.KoDeclaration
import com.lemonappdev.konsist.core.declaration.KoFunction
import com.lemonappdev.konsist.core.declaration.KoInterface
import com.lemonappdev.konsist.core.declaration.KoObject
import com.lemonappdev.konsist.core.declaration.KoProperty
import com.lemonappdev.konsist.core.exception.KoInternalException
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtDeclarationContainer
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtObjectDeclaration
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation

object KoDeclarationProviderUtil {
    inline fun <reified T : KoDeclaration> getKoDeclarations(
        ktDeclarationContainer: KtDeclarationContainer,
        modifiers: List<KoModifier>,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<T> {
        val declarations = ktDeclarationContainer
            .declarations
            .mapNotNull {
                if (it is KtClass && !it.isInterface()) {
                    KoClass.getInstance(it)
                } else if (it is KtClass && it.isInterface()) {
                    KoInterface.getInstance(it)
                } else if (it is KtObjectDeclaration && !it.isCompanion()) {
                    KoObject.getInstance(it)
                } else if (it is KtObjectDeclaration && it.isCompanion()) {
                    KoCompanionObject.getInstance(it)
                } else if (it is KtProperty) {
                    KoProperty.getInstance(it)
                } else if (it is KtFunction) {
                    KoFunction.getInstance(it)
                } else {
                    throw KoInternalException("Unknown declaration type: ${it.getTextWithLocation()}")
                }
            }
            .asSequence()

        return getKoDeclarations(declarations, modifiers, includeNested, includeLocal)
    }

    inline fun <reified T : KoDeclaration> getKoDeclarations(
        declarations: Sequence<KoDeclaration>,
        modifiers: List<KoModifier>,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<T> {
        var result = if (includeNested) {
            declarations.flatMap {
                when (it) {
                    is KoComplexDeclaration -> {
                        (listOf(it) + it.declarations(includeNested = true))
                    }

                    is T -> {
                        listOf(it)
                    }

                    else -> {
                        listOf()
                    }
                }
            }
        } else {
            declarations
        }

        if (includeLocal) {
            result = result
                .flatMap {
                    if (it is KoFunction) {
                        listOf(it) + it.localDeclarations() + localDeclarations(it.localFunctions())
                    } else {
                        listOf(it)
                    }
                }
                .filterIsInstance<T>()
        }

        if (modifiers.isNotEmpty()) {
            result = result.filter { it.hasModifiers(*modifiers.toTypedArray()) }
        }

        return result.filterIsInstance<T>()
    }

    fun localDeclarations(koFunctions: Sequence<KoFunction>): Sequence<KoDeclaration> {
        val localDeclarations = mutableListOf<KoDeclaration>()
        val nestedDeclarations = mutableListOf<KoDeclaration>()

        koFunctions.forEach { koFunction ->
            koFunction.localDeclarations().forEach {
                if (it is KoComplexDeclaration) {
                    nestedDeclarations += it.declarations(includeNested = true)
                }
            }

            localDeclarations += koFunction.localDeclarations() + nestedDeclarations + localDeclarations(koFunction.localFunctions())
        }

        return localDeclarations.asSequence()
    }
}
