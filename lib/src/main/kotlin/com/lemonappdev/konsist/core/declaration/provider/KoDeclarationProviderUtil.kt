package com.lemonappdev.konsist.core.declaration.provider

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoNamedDeclaration
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoCompanionObjectDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoComplexDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoImportDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoInterfaceDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoObjectDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoPackageDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoTypeAliasDeclarationImpl
import com.lemonappdev.konsist.core.exception.KoUnsupportedOperationException
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtDeclarationContainer
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtImportList
import org.jetbrains.kotlin.psi.KtObjectDeclaration
import org.jetbrains.kotlin.psi.KtPackageDirective
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeAlias
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation

internal object KoDeclarationProviderUtil {
    inline fun <reified T : KoNamedDeclaration> getKoDeclarations(
        ktElement: KtElement,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
        parent: KoBaseDeclaration,
    ): Sequence<T> {
        val declarations = ktElement
            .containingFile
            .children
            .filterNot { it is PsiWhiteSpace }
            .flattenDeclarations()
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
                } else if (it is KtImportDirective) {
                    KoImportDeclarationImpl.getInstance(it, parent)
                } else if (it is KtPackageDirective) {
                    KoPackageDeclarationImpl.getInstance(it, parent)
                } else if (it is KtAnnotationEntry){
                    KoAnnotationDeclarationImpl.getInstance(it, parent)
                }  else if (it is KtTypeAlias){
                    KoTypeAliasDeclarationImpl.getInstance(it, parent)
                } else {
                    throw KoUnsupportedOperationException("Unknown declaration type: ${it.getTextWithLocation()}")
                }
            }
            .asSequence()

        return getKoDeclarations(declarations, includeNested, includeLocal)
    }

    inline fun <reified T : KoNamedDeclaration> getKoDeclarations(
        declarations: Sequence<KoNamedDeclaration>,
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

        return result.filterIsInstance<T>()
    }

    fun localDeclarations(koFunctions: Sequence<KoFunctionDeclaration>): Sequence<KoNamedDeclaration> {
        val localDeclarations = mutableListOf<KoNamedDeclaration>()
        val nestedDeclarations = mutableListOf<KoNamedDeclaration>()

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

    private fun List<PsiElement>.flattenDeclarations() = this.flatMap {
        if (it is KtImportList) {
            it.imports
        } else {
            listOf(it)
        }
    }
}
