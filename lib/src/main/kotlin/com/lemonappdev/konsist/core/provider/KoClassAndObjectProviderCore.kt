package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoClassAndObjectProvider

internal interface KoClassAndObjectProviderCore :
    KoClassAndObjectProvider,
    KoBaseProviderCore,
    KoClassProviderCore,
    KoObjectProviderCore {
    override fun classesAndObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoClassAndObjectDeclaration> = classes(includeNested, includeLocal) + objects(includeNested)

    override fun numClassesAndObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int = classesAndObjects(includeNested, includeLocal).size

    override fun countClassesAndObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassAndObjectDeclaration) -> Boolean,
    ): Int = classesAndObjects(includeNested, includeLocal).count { predicate(it) }

    override fun hasClassesOrObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean = classesAndObjects(includeNested, includeLocal).isNotEmpty()

    override fun hasClassOrObjectWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean = hasClassOrObjectWithName(listOf(name, *names), includeNested, includeLocal)

    override fun hasClassOrObjectWithName(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasClassesOrObjects(includeNested, includeLocal)
            else ->
                names.any {
                    classesAndObjects(includeNested, includeLocal).any { koClass -> it == koClass.name }
                }
        }

    override fun hasClassesAndObjectsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean = hasClassesAndObjectsWithAllNames(listOf(name, *names), includeNested, includeLocal)

    override fun hasClassesAndObjectsWithAllNames(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasClassesOrObjects(includeNested, includeLocal)
            else ->
                names.all {
                    classesAndObjects(includeNested, includeLocal).any { koClass -> it == koClass.name }
                }
        }

    override fun hasClassOrObject(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassAndObjectDeclaration) -> Boolean,
    ): Boolean = classesAndObjects(includeNested, includeLocal).any(predicate)

    override fun hasAllClassesAndObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassAndObjectDeclaration) -> Boolean,
    ): Boolean = classesAndObjects(includeNested, includeLocal).all(predicate)
}
