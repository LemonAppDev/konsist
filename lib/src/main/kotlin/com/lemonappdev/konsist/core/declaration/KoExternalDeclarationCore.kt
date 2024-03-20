package com.lemonappdev.konsist.core.declaration

import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.core.cache.KoExternalDeclarationCache
import com.lemonappdev.konsist.core.declaration.type.KoBaseTypeDeclarationCore
import com.lemonappdev.konsist.core.provider.KoChildProviderCore
import com.lemonappdev.konsist.core.util.EndOfLine
import org.jetbrains.kotlin.psi.KtElement

internal class KoExternalDeclarationCore(name: String, override val ktElement: KtElement) :
    KoExternalDeclaration,
    KoParentDeclarationCore,
    KoChildProviderCore,
    KoBaseTypeDeclarationCore {
    override val psiElement: PsiElement by lazy { ktElement }

    override val name: String by lazy {
        name
            /**
             * Replace everything after '<' and '(' characters with empty string e.g.
             *
             * Foo(param) -> Foo
             * Foo<UiState> -> Foo
             * Foo<UiState, Action> -> Foo
             * Foo<UiState, Action>(Loading) -> Foo
             */
            .replace("\n", "")
            .replace(Regex("<.*|\\(.*"), "")
            .replace(EndOfLine.UNIX.value, " ")
            .substringBefore(" by")
    }

    override val packagee: KoPackageDeclaration? by lazy {
        KoPackageDeclarationCore(
            fullyQualifiedName,
            ktElement,
        )
    }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoExternalDeclarationCache = KoExternalDeclarationCache

        internal fun getInstance(
            name: String,
            ktElement: KtElement,
        ): KoExternalDeclaration =
            cache.getOrCreateInstance(name, ktElement) {
                KoExternalDeclarationCore(
                    name,
                    ktElement,
                )
            }
    }
}
