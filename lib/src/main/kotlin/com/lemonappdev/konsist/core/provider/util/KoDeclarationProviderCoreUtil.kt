package com.lemonappdev.konsist.core.provider.util

import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoEnumConstantDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoImportDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoInitBlockDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoInterfaceDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoObjectDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoPackageDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoSecondaryConstructorDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoTypeAliasDeclarationCore
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtAnonymousInitializer
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtDeclarationContainer
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtEnumEntry
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtFileAnnotationList
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtImportList
import org.jetbrains.kotlin.psi.KtObjectDeclaration
import org.jetbrains.kotlin.psi.KtPackageDirective
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtSecondaryConstructor
import org.jetbrains.kotlin.psi.KtTypeAlias

internal object KoDeclarationProviderCoreUtil {
    inline fun <reified T : KoBaseDeclaration> getKoDeclarations(
        ktElement: KtElement,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        containingDeclaration: KoBaseDeclaration,
    ): List<T> {
        val declarations: List<KoBaseDeclaration>

        return when (ktElement) {
            is KtFile -> {
                declarations =
                    ktElement
                        .containingFile
                        .children
                        .filterNot { it is PsiWhiteSpace }
                        .filterNot { it.text.isBlank() }
                        .flattenDeclarations()
                        .mapNotNull {
                            when (it) {
                                is KtDeclaration -> getInstanceOfKtDeclaration(it, containingDeclaration)
                                else -> getInstanceOfOtherDeclaration(it, containingDeclaration)
                            }
                        }
                getKoDeclarations(declarations, includeNested, includeLocal)
            }

            is KtClass -> {
                val propertiesFromConstructor =
                    ktElement
                        .primaryConstructorParameters
                        .filter { it.hasValOrVar() }
                        .map { KoPropertyDeclarationCore.getInstance(it, containingDeclaration) }

                declarations =
                    ktElement
                        .declarations
                        .mapNotNull { getInstanceOfKtDeclaration(it, containingDeclaration) }

                getKoDeclarations(propertiesFromConstructor + declarations, includeNested, includeLocal)
            }

            is KtDeclarationContainer -> {
                declarations =
                    ktElement
                        .declarations
                        .mapNotNull { getInstanceOfKtDeclaration(it, containingDeclaration) }
                getKoDeclarations(declarations, includeNested, includeLocal)
            }

            else -> emptyList()
        }
    }

    inline fun <reified T : KoBaseDeclaration> getKoDeclarations(
        declarations: List<KoBaseDeclaration>,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): List<T> {
        var result =
            if (includeNested) {
                declarations.flatMap {
                    when (it) {
                        is KoDeclarationProvider -> listOf(it) + it.declarations(includeNested = true, includeLocal = false)
                        else -> listOf(it)
                    }
                }
            } else {
                declarations
            }

        if (includeLocal) {
            result =
                result
                    .flatMap {
                        when (it) {
                            is KoFunctionDeclarationCore -> {
                                val localDeclarations =
                                    listOf(it) + it.localDeclarations +
                                        localDeclarations(
                                            it.localFunctions,
                                            includeNested,
                                        )

                                if (includeNested) {
                                    localDeclarations + nestedDeclarations(it.localDeclarations)
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

    fun nestedDeclarations(koNamedDeclarations: List<KoBaseDeclaration>): List<KoBaseDeclaration> {
        return koNamedDeclarations.flatMap {
            when (it) {
                is KoDeclarationProvider -> it.declarations(includeNested = true, includeLocal = false)
                else -> emptyList()
            }
        }
    }

    fun localDeclarations(
        koFunctions: List<KoFunctionDeclaration>,
        includeNested: Boolean,
    ): List<KoBaseDeclaration> {
        val localDeclarations = mutableListOf<KoBaseDeclaration>()
        val nestedDeclarations = mutableListOf<KoBaseDeclaration>()

        koFunctions.forEach { koFunction ->
            koFunction.localDeclarations.forEach {
                if (it is KoDeclarationProvider && includeNested) {
                    nestedDeclarations += it.declarations(includeNested = true, includeLocal = true)
                }
            }

            localDeclarations += koFunction.localDeclarations + nestedDeclarations +
                localDeclarations(
                    koFunction.localFunctions,
                    includeNested,
                )
        }

        return localDeclarations
    }

    private fun List<PsiElement>.flattenDeclarations(): List<PsiElement> =
        this.flatMap {
            when (it) {
                is KtImportList -> it.imports
                is KtFileAnnotationList -> it.annotationEntries
                else -> listOf(it)
            }
        }

    private fun getInstanceOfKtDeclaration(
        ktDeclaration: KtDeclaration,
        containingDeclaration: KoBaseDeclaration,
    ): KoBaseDeclaration? =
        when {
            ktDeclaration is KtEnumEntry -> KoEnumConstantDeclarationCore.getInstance(ktDeclaration, containingDeclaration)
            ktDeclaration is KtSecondaryConstructor ->
                KoSecondaryConstructorDeclarationCore.getInstance(
                    ktDeclaration,
                    containingDeclaration,
                )

            ktDeclaration is KtClass && !ktDeclaration.isInterface() ->
                KoClassDeclarationCore.getInstance(
                    ktDeclaration,
                    containingDeclaration,
                )

            ktDeclaration is KtClass && ktDeclaration.isInterface() ->
                KoInterfaceDeclarationCore.getInstance(
                    ktDeclaration,
                    containingDeclaration,
                )

            ktDeclaration is KtObjectDeclaration ->
                KoObjectDeclarationCore.getInstance(
                    ktDeclaration,
                    containingDeclaration,
                )

            ktDeclaration is KtProperty -> KoPropertyDeclarationCore.getInstance(ktDeclaration, containingDeclaration)
            ktDeclaration is KtFunction -> KoFunctionDeclarationCore.getInstance(ktDeclaration, containingDeclaration)
            ktDeclaration is KtTypeAlias -> KoTypeAliasDeclarationCore.getInstance(ktDeclaration, containingDeclaration)
            ktDeclaration is KtAnonymousInitializer ->
                KoInitBlockDeclarationCore.getInstance(
                    ktDeclaration,
                    containingDeclaration,
                )

            else -> null
        }

    private fun getInstanceOfOtherDeclaration(
        psiElement: PsiElement,
        containingDeclaration: KoBaseDeclaration,
    ): KoBaseDeclaration? =
        when (psiElement) {
            is KtImportDirective -> KoImportDeclarationCore.getInstance(psiElement, containingDeclaration)
            is KtPackageDirective -> KoPackageDeclarationCore.getInstance(psiElement, containingDeclaration)
            is KtAnnotationEntry -> KoAnnotationDeclarationCore.getInstance(psiElement, containingDeclaration)
            else -> null
        }
}
