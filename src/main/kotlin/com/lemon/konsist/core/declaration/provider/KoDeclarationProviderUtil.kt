package com.lemon.konsist.core.declaration.provider

import com.lemon.konsist.core.const.KoModifier
import com.lemon.konsist.core.declaration.KoClass
import com.lemon.konsist.core.declaration.KoCompanionObject
import com.lemon.konsist.core.declaration.KoComplexDeclaration
import com.lemon.konsist.core.declaration.KoDeclaration
import com.lemon.konsist.core.declaration.KoFunction
import com.lemon.konsist.core.declaration.KoInterface
import com.lemon.konsist.core.declaration.KoObject
import com.lemon.konsist.core.declaration.KoProperty
import com.lemon.konsist.core.declaration.logger.KoLogger
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
    ): List<T> {
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
                    KoLogger.logError("Unknown declaration type: ${it.getTextWithLocation()}")
                    null
                }
            }

        return getKoDeclarations(declarations, modifiers, includeNested, includeLocal)
    }

    inline fun <reified T : KoDeclaration> getKoDeclarations(
        declarations: List<KoDeclaration>,
        modifiers: List<KoModifier>,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): List<T> {
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

    fun localDeclarations(koFunctions: List<KoFunction>): List<KoDeclaration> {
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

        return localDeclarations
    }
}
