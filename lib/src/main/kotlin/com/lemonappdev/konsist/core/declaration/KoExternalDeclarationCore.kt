package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.core.cache.KoExternalDeclarationCache
import com.lemonappdev.konsist.core.cache.KoPropertyCache
import com.lemonappdev.konsist.core.declaration.type.KoBaseTypeDeclarationCore
import com.lemonappdev.konsist.core.provider.KoChildProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.util.EndOfLine
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement

internal class KoExternalDeclarationCore private constructor(
    private val rawName: String,
    override val fullyQualifiedName: String,
    override val ktElement: KtElement,
) : KoExternalDeclaration,
    KoChildProviderCore,
    KoBaseTypeDeclarationCore,
    KoFullyQualifiedNameProviderCore {
    override val psiElement: PsiElement
        get() = ktElement

    @Volatile
    private var cachedName: Any? = KoPropertyCache.NOT_COMPUTED

    override val name: String
        get() =
            KoPropertyCache.getOrCompute(cachedName, { cachedName = it }) {
                rawName
                    /*
                     * Replace everything after '<' and '(' characters with empty string e.g.
                     *
                     * Foo(param) -> Foo
                     * Foo<UiState> -> Foo
                     * Foo<UiState, Action> -> Foo
                     * Foo<UiState, Action>(Loading) -> Foo
                     *
                     * Qualified name is replaced with the simple name, e.g. RecyclerView.ViewHolder -> ViewHolder
                     */
                    .replace("\n", "")
                    .replace(Regex("<.*|\\(.*"), "")
                    .replace(EndOfLine.UNIX.value, " ")
                    .substringBefore(" by")
                    .substringAfterLast(".")
            }

    @Volatile
    private var cachedPackagee: Any? = KoPropertyCache.NOT_COMPUTED

    override val packagee: KoPackageDeclaration?
        get() =
            KoPropertyCache.getOrCompute(cachedPackagee, { cachedPackagee = it }) {
                KoPackageDeclarationCore(
                    fullyQualifiedName,
                    ktElement,
                )
            }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoExternalDeclarationCache = KoExternalDeclarationCache

        /*
         * Declarations are cached by fully qualified name, because the same name can refer to different types
         * in different files (e.g. `Foo` imported from `aaa.Foo` and `bbb.Foo`).
         */
        internal fun getInstance(
            name: String,
            fullyQualifiedName: String,
            ktElement: KtElement,
        ): KoExternalDeclaration =
            cache.getOrCreateInstance(fullyQualifiedName) {
                KoExternalDeclarationCore(name, fullyQualifiedName, ktElement)
            }
    }
}
