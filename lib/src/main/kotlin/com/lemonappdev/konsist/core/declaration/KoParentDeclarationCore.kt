package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.util.EndOfLine
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

internal class KoParentDeclarationCore private constructor(private val ktSuperTypeListEntry: KtSuperTypeListEntry) :
    KoParentDeclaration,
    KoBaseProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoNameProviderCore,
    KoLocationProviderCore,
    KoPathProviderCore {

    override val psiElement: PsiElement
        get() = ktSuperTypeListEntry

    override val ktElement: KtElement
        get() = ktSuperTypeListEntry

    override val name: String
        get() = ktSuperTypeListEntry
            .text
            /**
             * Replace everything after '<' and '(' characters with empty string e.g.
             *
             * Foo(param) -> Foo
             * Foo<UiState> -> Foo
             * Foo<UiState, Action> -> Foo
             * Foo<UiState, Action>(Loading) -> Foo
             */
            .replace(Regex("<.*|\\(.*"), "")
            .replace(EndOfLine.UNIX.value, " ")
            .substringBefore(" by")

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoParentDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktSuperTypeListEntry: KtSuperTypeListEntry,
            containingDeclaration: KoContainingDeclarationProvider,
        ): KoParentDeclaration =
            cache.getOrCreateInstance(
                ktSuperTypeListEntry,
                containingDeclaration,
            ) { KoParentDeclarationCore(ktSuperTypeListEntry) }
    }
}
