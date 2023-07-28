package com.lemonappdev.konsist.core.provider.util

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoImportDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoInitBlockDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoInterfaceDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoObjectDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoPackageDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoTypeAliasDeclarationImpl
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtAnonymousInitializer
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

internal object KoDeclarationProviderCoreUtil {
    inline fun <reified T : KoBaseDeclaration> getKoDeclarations(
        ktElement: KtElement,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
        parent: KoParentProvider?,
    ): Sequence<T> {
        val declarations: Sequence<KoBaseDeclaration>

        return when (ktElement) {
            is KtFile -> {
                declarations = ktElement
                    .containingFile
                    .children
                    .filterNot { it is PsiWhiteSpace }
                    .filterNot { it.text.isBlank() }
                    .flattenDeclarations()
                    .mapNotNull {
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

            is KtAnonymousInitializer -> {
                declarations = ktElement
                    .body
                    ?.children
                    ?.filterIsInstance<KtDeclaration>()
                    ?.mapNotNull { getInstanceOfKtDeclaration(it, parent) }
                    ?.asSequence()
                    ?: emptySequence()

                getKoDeclarations(declarations, includeNested, includeLocal)
            }

            else -> emptySequence()
        }
    }

    inline fun <reified T : KoBaseDeclaration> getKoDeclarations(
        declarations: Sequence<KoBaseDeclaration>,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<T> {
        var result = if (includeNested) {
            declarations.flatMap {
                when (it) {
                    is KoDeclarationProvider -> sequenceOf(it) + it.declarations(includeNested = true)
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

    fun nestedDeclarations(koNamedDeclarations: Sequence<KoBaseDeclaration>): Sequence<KoBaseDeclaration> {
        return koNamedDeclarations.flatMap {
            when (it) {
                is KoDeclarationProvider -> it.declarations(includeNested = true)
                else -> sequenceOf()
            }
        }
    }

    fun localDeclarations(koFunctions: Sequence<KoFunctionDeclaration>, includeNested: Boolean): Sequence<KoBaseDeclaration> {
        val localDeclarations = mutableListOf<KoBaseDeclaration>()
        val nestedDeclarations = mutableListOf<KoBaseDeclaration>()

        koFunctions.forEach { koFunction ->
            koFunction.localDeclarations().forEach {
                if (it is KoDeclarationProvider && includeNested) {
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

    private fun getInstanceOfKtDeclaration(
        ktDeclaration: KtDeclaration,
        parent: KoParentProvider?,
    ): KoBaseDeclaration? = when {
        ktDeclaration is KtClass && !ktDeclaration.isInterface() -> KoClassDeclarationImpl.getInstance(ktDeclaration, parent)
        ktDeclaration is KtClass && ktDeclaration.isInterface() -> KoInterfaceDeclarationImpl.getInstance(ktDeclaration, parent)
        ktDeclaration is KtObjectDeclaration -> KoObjectDeclarationImpl.getInstance(ktDeclaration, parent)
        ktDeclaration is KtProperty -> KoPropertyDeclarationImpl.getInstance(ktDeclaration, parent)
        ktDeclaration is KtFunction -> KoFunctionDeclarationImpl.getInstance(ktDeclaration, parent)
        ktDeclaration is KtTypeAlias -> KoTypeAliasDeclarationImpl.getInstance(ktDeclaration, parent)
        ktDeclaration is KtAnonymousInitializer -> KoInitBlockDeclarationImpl.getInstance(ktDeclaration, parent)
        else -> null
    }

    private fun getInstanceOfOtherDeclaration(psiElement: PsiElement, parent: KoParentProvider?): KoBaseDeclaration? =
        when (psiElement) {
            is KtImportDirective -> KoImportDeclarationImpl.getInstance(psiElement, parent)
            is KtPackageDirective -> KoPackageDeclarationImpl.getInstance(psiElement, parent)
            is KtAnnotationEntry -> KoAnnotationDeclarationImpl.getInstance(psiElement, parent)
            else -> null
        }
}
