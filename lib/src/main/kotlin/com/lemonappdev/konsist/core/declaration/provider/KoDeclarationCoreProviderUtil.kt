package com.lemonappdev.konsist.core.declaration.provider

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoNamedDeclaration
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationImpl
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
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtDeclarationContainer
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtFileAnnotationList
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtImportList
import org.jetbrains.kotlin.psi.KtObjectDeclaration
import org.jetbrains.kotlin.psi.KtPackageDirective
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeAlias
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation

internal object KoDeclarationCoreProviderUtil {
    inline fun <reified T : KoNamedDeclaration> getKoDeclarations(
        ktElement: KtElement,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
        parent: KoBaseDeclaration,
    ): Sequence<T> {
        val declarations: Sequence<KoNamedDeclaration>

        return when (ktElement) {
            is KtFile -> {
                declarations = ktElement
                    .containingFile
                    .children
                    .filterNot { it is PsiWhiteSpace }
                    .filterNot { it.text.isBlank() }
                    .flattenDeclarations()
                    .map {
                        when (it) {
                            is KtDeclaration -> getInstanceOfKtDeclaration(it, parent)
                            else -> getInstanceOfOtherDeclaration(it, parent)
                        }
                    }
                    .asSequence()
                getKoDeclarations(declarations, includeNested, includeLocal)
            }

            is KtDeclarationContainer -> {
                declarations = ktElement
                    .declarations
                    .mapNotNull { getInstanceOfKtDeclaration(it, parent) }
                    .asSequence()
                getKoDeclarations(declarations, includeNested, includeLocal)
            }

            else -> emptySequence()
        }
    }

    inline fun <reified T : KoNamedDeclaration> getKoDeclarations(
        declarations: Sequence<KoNamedDeclaration>,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<T> {
        var result = if (includeNested) {
            declarations.flatMap {
                when (it) {
                    is KoComplexDeclarationImpl -> sequenceOf(it) + it.declarations(includeNested = true)
                    else -> sequenceOf(it)
                }
            }
        } else {
            declarations
        }

        if (includeLocal) {
            result = result
                .flatMap {
                    when (it) {
                        is KoFunctionDeclarationImpl -> {
                            val localDeclarations = listOf(it) + it.localDeclarations() + localDeclarations(
                                it.localFunctions(),
                                includeNested,
                            )

                            if (includeNested) {
                                localDeclarations + nestedDeclarations(it.localDeclarations())
                            } else {
                                localDeclarations
                            }
                        }

                        else -> listOf(it)
                    }
                }
                .filterIsInstance<T>()
        }

        return result.filterIsInstance<T>()
    }

    fun nestedDeclarations(koNamedDeclarations: Sequence<KoNamedDeclaration>): Sequence<KoNamedDeclaration> {
        return koNamedDeclarations.flatMap {
            when (it) {
                is KoComplexDeclarationImpl -> it.declarations(includeNested = true)
                else -> sequenceOf()
            }
        }
    }

    fun localDeclarations(koFunctions: Sequence<KoFunctionDeclaration>, includeNested: Boolean): Sequence<KoNamedDeclaration> {
        val localDeclarations = mutableListOf<KoNamedDeclaration>()
        val nestedDeclarations = mutableListOf<KoNamedDeclaration>()

        koFunctions.forEach { koFunction ->
            koFunction.localDeclarations().forEach {
                if (it is KoComplexDeclarationImpl && includeNested) {
                    nestedDeclarations += it.declarations(includeNested = true, includeLocal = true)
                }
            }

            localDeclarations += koFunction.localDeclarations() + nestedDeclarations + localDeclarations(
                koFunction.localFunctions(),
                includeNested,
            )
        }

        return localDeclarations.asSequence()
    }

    private fun List<PsiElement>.flattenDeclarations(): List<PsiElement> = this.flatMap {
        when (it) {
            is KtImportList -> it.imports
            is KtFileAnnotationList -> it.annotationEntries
            else -> listOf(it)
        }
    }

    private fun getInstanceOfKtDeclaration(ktDeclaration: KtDeclaration, parent: KoBaseDeclaration): KoDeclaration = when {
        ktDeclaration is KtClass && !ktDeclaration.isInterface() -> KoClassDeclarationImpl.getInstance(ktDeclaration, parent)
        ktDeclaration is KtClass && ktDeclaration.isInterface() -> KoInterfaceDeclarationImpl.getInstance(ktDeclaration, parent)
        ktDeclaration is KtObjectDeclaration -> KoObjectDeclarationImpl.getInstance(ktDeclaration, parent)
        ktDeclaration is KtProperty -> KoPropertyDeclarationImpl.getInstance(ktDeclaration, parent)
        ktDeclaration is KtFunction -> KoFunctionDeclarationImpl.getInstance(ktDeclaration, parent)
        ktDeclaration is KtTypeAlias -> KoTypeAliasDeclarationImpl.getInstance(ktDeclaration, parent)
        else -> throw KoUnsupportedOperationException("Unknown declaration type: ${ktDeclaration.getTextWithLocation()}")
    }

    private fun getInstanceOfOtherDeclaration(psiElement: PsiElement, parent: KoBaseDeclaration): KoNamedDeclaration = when (psiElement) {
        is KtImportDirective -> KoImportDeclarationImpl.getInstance(psiElement, parent)
        is KtPackageDirective -> KoPackageDeclarationImpl.getInstance(psiElement, parent)
        is KtAnnotationEntry -> KoAnnotationDeclarationImpl.getInstance(psiElement, parent)
        else -> throw KoUnsupportedOperationException("Unknown declaration type: ${psiElement.getTextWithLocation()}")
    }
}
