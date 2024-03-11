package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoParentInterfaceDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDelegateProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.util.EndOfLine
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

@Deprecated("Will be removed in v0.16.0")
internal class KoParentInterfaceDeclarationCore private constructor(private val ktSuperTypeListEntry: KtSuperTypeListEntry) :
    KoParentInterfaceDeclaration,
    KoDelegateProviderCore,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoFullyQualifiedNameProviderCore,
    KoNameProviderCore,
    KoLocationProviderCore,
    KoPackageDeclarationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoResideInPackageProviderCore {
        override val psiElement: PsiElement
            get() = ktSuperTypeListEntry
        override val ktElement: KtElement
            get() = ktSuperTypeListEntry

        override val fullyQualifiedName: String by lazy {
            containingFile
                .imports
                .firstOrNull { it.text.endsWith(".$name") }
                ?.name ?: ""
        }

        override val name: String
            get() =
                ktSuperTypeListEntry
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

        override val delegateName: String?
            get() =
                if (ktSuperTypeListEntry is KtDelegatedSuperTypeEntry) {
                    ktSuperTypeListEntry
                        .delegateExpression
                        ?.text
                } else {
                    null
                }

        override fun toString(): String = name

        internal companion object {
            private val cache: KoDeclarationCache<KoParentInterfaceDeclaration> = KoDeclarationCache()

            internal fun getInstance(
                ktSuperTypeListEntry: KtSuperTypeListEntry,
                containingDeclaration: KoBaseDeclaration,
            ): KoParentInterfaceDeclaration =
                cache.getOrCreateInstance(
                    ktSuperTypeListEntry,
                    containingDeclaration,
                ) { KoParentInterfaceDeclarationCore(ktSuperTypeListEntry) }
        }
    }
