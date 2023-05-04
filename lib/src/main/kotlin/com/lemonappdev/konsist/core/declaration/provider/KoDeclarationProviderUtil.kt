package com.lemonappdev.konsist.core.declaration.provider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.core.declaration.KoBaseDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoCompanionObjectDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoComplexDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoInterfaceDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoObjectDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationImpl
import com.lemonappdev.konsist.core.exception.KoUnsupportedOperationException
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtDeclarationContainer
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtObjectDeclaration
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation

internal object KoDeclarationProviderUtil {
    inline fun <reified T : KoDeclaration> getKoDeclarations(
        ktDeclarationContainer: KtDeclarationContainer,
        modifiers: List<KoModifier>,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
        parent: KoBaseDeclarationImpl,
    ): Sequence<T> {
        val declarations = ktDeclarationContainer
            .declarations
            .mapNotNull {
                if (it is KtClass && !it.isInterface()) {
                    KoClassDeclarationImpl.getInstance(it, parent)
                } else if (it is KtClass && it.isInterface()) {
                    KoInterfaceDeclarationImpl.getInstance(it, parent)
                } else if (it is KtObjectDeclaration && !it.isCompanion()) {
                    KoObjectDeclarationImpl.getInstance(it, parent)
                } else if (it is KtObjectDeclaration && it.isCompanion()) {
                    KoCompanionObjectDeclarationImpl.getInstance(it, parent)
                } else if (it is KtProperty) {
                    KoPropertyDeclarationImpl.getInstance(it, parent)
                } else if (it is KtFunction) {
                    KoFunctionDeclarationImpl.getInstance(it, parent)
                } else {
                    throw KoUnsupportedOperationException("Unknown declaration type: ${it.getTextWithLocation()}")
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
                    is KoComplexDeclarationImpl -> {
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
                    if (it is KoFunctionDeclarationImpl) {
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

    fun localDeclarations(koFunctions: Sequence<KoFunctionDeclaration>): Sequence<KoDeclaration> {
        val localDeclarations = mutableListOf<KoDeclaration>()
        val nestedDeclarations = mutableListOf<KoDeclaration>()

        koFunctions.forEach { koFunction ->
            koFunction.localDeclarations().forEach {
                if (it is KoComplexDeclarationImpl) {
                    nestedDeclarations += it.declarations(includeNested = true)
                }
            }

            localDeclarations += koFunction.localDeclarations() + nestedDeclarations + localDeclarations(koFunction.localFunctions())
        }

        return localDeclarations.asSequence()
    }
}
