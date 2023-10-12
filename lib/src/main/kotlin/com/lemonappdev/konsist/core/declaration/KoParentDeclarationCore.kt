package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore

// internal open class KoParentDeclarationCore private constructor(private val ktSuperTypeListEntry: KtSuperTypeListEntry) :
//    KoParentDeclaration,
//    KoBaseProviderCore,
//    KoFullyQualifiedNameProviderCore,
//    KoNameProviderCore {
//    override val ktElement: KtElement
//        get() = ktSuperTypeListEntry
//
//    override val name: String
//        get() = ktSuperTypeListEntry
//            .text
//            /**
//             * Replace everything after '<' and '(' characters with empty string e.g.
//             *
//             * Foo(param) -> Foo
//             * Foo<UiState> -> Foo
//             * Foo<UiState, Action> -> Foo
//             * Foo<UiState, Action>(Loading) -> Foo
//             */
//            .replace("\n", "")
//            .replace(Regex("<.*|\\(.*"), "")
//            .replace(EndOfLine.UNIX.value, " ")
//            .substringBefore(" by")
//
//    override fun toString(): String = name
//
//    internal companion object {
//        private val cache: KoDeclarationCache<KoParentDeclaration> = KoDeclarationCache()
//
//        internal fun getInstance(
//            ktSuperTypeListEntry: KtSuperTypeListEntry,
//            containingDeclaration: KoContainingDeclarationProvider,
//        ): KoParentDeclaration =
//            cache.getOrCreateInstance(
//                ktSuperTypeListEntry,
//                containingDeclaration,
//            ) { KoParentDeclarationCore(ktSuperTypeListEntry) }
//    }
// }

internal interface KoParentDeclarationCore :
    KoParentDeclaration,
    KoBaseProviderCore,
    KoFullyQualifiedNameProviderCore,
    KoNameProviderCore
