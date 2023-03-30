package com.lemon.konsist.core.util

import com.lemon.konsist.core.declaration.KoClass
import com.lemon.konsist.core.declaration.KoComplexDeclaration
import com.lemon.konsist.core.declaration.KoDeclaration
import com.lemon.konsist.core.declaration.KoFunction
import com.lemon.konsist.core.declaration.KoInterface
import com.lemon.konsist.core.declaration.KoObject
import com.lemon.konsist.core.declaration.KoProperty
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtDeclarationContainer
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtObjectDeclaration
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation

object KoDeclarationUtil {
    inline fun <reified T : KoDeclaration> getKoDeclarations(
        ktDeclarationContainer: KtDeclarationContainer,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): List<T> {
        val declarations = ktDeclarationContainer
            .declarations
            .mapNotNull {
                if (it is KtClass && !it.isInterface()) {
                    KoClass(it)
                } else if (it is KtClass && it.isInterface()) {
                    KoInterface(it)
                } else if (it is KtObjectDeclaration) {
                    KoObject(it)
                } else if (it is KtProperty) {
                    KoProperty(it)
                } else if (it is KtFunction) {
                    KoFunction(it)
                } else {
                    println("Warning unsupported kt declaration: ${it.getTextWithLocation()}")
                    null
                }
            }

        return getKoDeclarations(declarations, includeNested, includeLocal)
    }

    inline fun <reified T : KoDeclaration> getKoDeclarations(
        declarations: List<KoDeclaration>,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): List<T> {
        var result = if (includeNested) {
            declarations.flatMap {
                when (it) {
                    is KoComplexDeclaration -> (listOf(it) + it.declarations(true)).filterIsInstance<T>()
                    is T -> listOf(it)
                    else -> listOf()
                }
            }
        } else {
            declarations.filterIsInstance<T>()
        }

        if (includeLocal) {
            result = result
                .filterIsInstance<KoFunction>()
                .flatMap { listOf(it) + it.getLocalDeclarations() }
                .filterIsInstance<T>()
        }

        return result
    }
}
