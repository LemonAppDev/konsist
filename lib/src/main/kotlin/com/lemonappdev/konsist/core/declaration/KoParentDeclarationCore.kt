package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.util.EndOfLine
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

internal open class KoParentDeclarationCore private constructor(private val ktSuperTypeListEntry: KtSuperTypeListEntry) :
    KoParentDeclaration,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoFullyQualifiedNameProviderCore,
    KoNameProviderCore,
    KoLocationProviderCore,
    KoPackageDeclarationProviderCore,
    KoPathProviderCore,
    KoResideInPackageProviderCore,
    KoResideInOrOutsidePackageProviderCore {

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
            .replace("\n", "")
            .replace(Regex("<.*|\\(.*"), "")
            .replace(EndOfLine.UNIX.value, " ")
            .substringBefore(" by")

    override val fullyQualifiedName: String by lazy {
        containingFile
            .imports
            .firstOrNull { it.text.endsWith(".$name") }
            ?.name ?: name
    }

    /*
    1.0.0 CleanUp - Now declaration implements two providers - KoResideInPackageProvider and KoResideInOrOutsidePackageProvider
    (the second one is deprecated) - with the same methods, so we must override this and choose which implementation
    this method should have. After removing deprecated provider in v1.0.0 it will be unnecessary.
     */
    override fun resideInPackage(name: String): Boolean {
        return super<KoResideInPackageProviderCore>.resideInPackage(name)
    }

    /*
    1.0.0 CleanUp - Now declaration implements two providers - KoResideInPackageProvider and KoResideInOrOutsidePackageProvider
    (the second one is deprecated) - with the same methods, so we must override this and choose which implementation
    this method should have. After removing deprecated provider in v1.0.0 it will be unnecessary.
     */
    override fun resideOutsidePackage(name: String): Boolean {
        return super<KoResideInPackageProviderCore>.resideOutsidePackage(name)
    }

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
