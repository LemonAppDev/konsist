package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalClassProvider

internal interface KoLocalClassProviderCore :
    KoLocalClassProvider,
    KoLocalDeclarationProviderCore,
    KoBaseProviderCore {
    override val localClasses: List<KoClassDeclaration>
        get() = localDeclarations.filterIsInstance<KoClassDeclaration>()

    override val numLocalClasses: Int
        get() = localClasses.size

    override fun countLocalClasses(predicate: (KoClassDeclaration) -> Boolean): Int = localClasses.count { predicate(it) }

    override fun hasLocalClasses(): Boolean = localClasses.isNotEmpty()

    override fun hasLocalClassWithName(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = hasLocalClassWithName(listOf(name, *names), ignoreCase)

    override fun hasLocalClassWithName(
        names: Collection<String>,
        ignoreCase: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasLocalClasses()
            else ->
                names.any {
                    localClasses.any { localClass -> localClass.hasName(it, ignoreCase) }
                }
        }

    override fun hasLocalClassesWithAllNames(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = hasLocalClassesWithAllNames(listOf(name, *names), ignoreCase)

    override fun hasLocalClassesWithAllNames(
        names: Collection<String>,
        ignoreCase: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasLocalClasses()
            else ->
                names.all {
                    localClasses.any { localClass -> localClass.hasName(it, ignoreCase) }
                }
        }

    override fun hasLocalClass(predicate: (KoClassDeclaration) -> Boolean): Boolean = localClasses.any(predicate)

    override fun hasAllLocalClasses(predicate: (KoClassDeclaration) -> Boolean): Boolean = localClasses.all(predicate)
}
