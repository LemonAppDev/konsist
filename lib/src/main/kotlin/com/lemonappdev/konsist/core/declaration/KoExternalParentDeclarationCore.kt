package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoExternalParentDeclaration
import com.lemonappdev.konsist.core.util.EndOfLine
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

internal class KoExternalParentDeclarationCore(name: String, private val ktSuperTypeListEntry: KtSuperTypeListEntry) :
    KoExternalParentDeclaration,
    KoParentDeclarationCore {
    override val ktElement: KtElement by lazy { ktSuperTypeListEntry }

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

    override fun toString(): String = name
}
