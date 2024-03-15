@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoChildDeclaration
import com.lemonappdev.konsist.api.provider.KoChildProvider
import kotlin.reflect.KClass

/**
 * List containing child declarations.
 *
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing child declarations.
 */
fun <T : KoChildProvider> List<T>.children(indirectChildren: Boolean = false): List<KoChildDeclaration> =
    flatMap { it.children(indirectChildren) }

/**
 * List containing declarations with class or interface child.
 *
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations with class or interface child.
 */
fun <T : KoChildProvider> List<T>.withChildren(indirectChildren: Boolean = false): List<T> =
    filter { it.hasChildren(indirectChildren) }

/**
 * List containing declarations with no child - class does not extend any class and does not implement any interface.
 *
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations with no child - class does not extend any class and does not implement any
 * interface.
 */
fun <T : KoChildProvider> List<T>.withoutChildren(indirectChildren: Boolean = false): List<T> =
    filterNot { it.hasChildren(indirectChildren) }

/**
 * List containing declarations that have at least one child with the specified name(s).
 *
 * @param name The name of the child to include.
 * @param names The names of additional children to include.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations with at least one of the specified child(s).
 */
fun <T : KoChildProvider> List<T>.withChildNamed(
    name: String,
    vararg names: String,
    indirectChildren: Boolean = false,
): List<T> =
    filter {
        it.hasChildWithName(name, *names, indirectChildren = indirectChildren)
    }

/**
 * List containing declarations that have at least one child with the specified name(s).
 *
 * @param names The names of additional children to include.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations with at least one of the specified child(s).
 */
fun <T : KoChildProvider> List<T>.withChildNamed(names: Set<String>, indirectChildren: Boolean = false): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasChildren(indirectChildren = indirectChildren)
            else -> it.hasChildWithName(
                names.first(),
                *names.drop(1).toTypedArray(),
                indirectChildren = indirectChildren
            )
        }
    }

/**
 * List containing declarations that have at least one child with the specified name(s).
 *
 * @param names The names of additional children to include.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations with at least one of the specified child(s).
 */
fun <T : KoChildProvider> List<T>.withChildNamed(names: List<String>, indirectChildren: Boolean = false): List<T> =
    withChildNamed(names.toSet(), indirectChildren)

/**
 * List containing declarations without any of specified children.
 *
 * @param name The name of the child to exclude.
 * @param names The names of additional children to exclude.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations without any of specified children.
 */
fun <T : KoChildProvider> List<T>.withoutChildNamed(
    name: String,
    vararg names: String,
    indirectChildren: Boolean = false,
): List<T> =
    filterNot {
        it.hasChildWithName(name, *names, indirectChildren = indirectChildren)
    }

/**
 * List containing declarations without any of specified children.
 *
 * @param names The names of additional children to exclude.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations without any of specified children.
 */
fun <T : KoChildProvider> List<T>.withoutChildNamed(names: Set<String>, indirectChildren: Boolean = false): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasChildren(indirectChildren = indirectChildren)
            else -> it.hasChildWithName(
                names.first(),
                *names.drop(1).toTypedArray(),
                indirectChildren = indirectChildren
            )
        }
    }

/**
 * List containing declarations without any of specified children.
 *
 * @param names The names of additional children to exclude.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations without any of specified children.
 */
fun <T : KoChildProvider> List<T>.withoutChildNamed(names: List<String>, indirectChildren: Boolean = false): List<T> =
    withoutChildNamed(names.toSet(), indirectChildren)

/**
 * List containing declarations that have all specified children.
 *
 * @param name The name of the child to include.
 * @param names The name(s) of the child(s) to include.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations with all specified child(s).
 */
fun <T : KoChildProvider> List<T>.withAllChildrenNamed(
    name: String,
    vararg names: String,
    indirectChildren: Boolean = false,
): List<T> =
    filter {
        it.hasChildrenWithAllNames(name, *names, indirectChildren = indirectChildren)
    }

/**
 * List containing declarations that have all specified children.
 *
 * @param names The name(s) of the child(s) to include.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations with all specified child(s).
 */
fun <T : KoChildProvider> List<T>.withAllChildrenNamed(names: Set<String>, indirectChildren: Boolean = false): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasChildren(indirectChildren = indirectChildren)
            else -> it.hasChildrenWithAllNames(
                names.first(),
                *names.drop(1).toTypedArray(),
                indirectChildren = indirectChildren
            )
        }
    }

/**
 * List containing declarations that have all specified children.
 *
 * @param names The name(s) of the child(s) to include.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations with all specified child(s).
 */
fun <T : KoChildProvider> List<T>.withAllChildrenNamed(
    names: List<String>,
    indirectChildren: Boolean = false
): List<T> =
    withAllChildrenNamed(names.toSet(), indirectChildren)

/**
 * List containing declarations without all specified children.
 *
 * @param name The name of the child to exclude.
 * @param names The name(s) of the child(s) to exclude.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations without all specified child(s).
 */
fun <T : KoChildProvider> List<T>.withoutAllChildrenNamed(
    name: String,
    vararg names: String,
    indirectChildren: Boolean = false,
): List<T> =
    filterNot {
        it.hasChildrenWithAllNames(name, *names, indirectChildren = indirectChildren)
    }

/**
 * List containing declarations without all specified children.
 *
 * @param names The name(s) of the child(s) to exclude.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations without all specified child(s).
 */
fun <T : KoChildProvider> List<T>.withoutAllChildrenNamed(
    names: Set<String>,
    indirectChildren: Boolean = false
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasChildren(indirectChildren = indirectChildren)
            else -> it.hasChildrenWithAllNames(
                names.first(),
                *names.drop(1).toTypedArray(),
                indirectChildren = indirectChildren
            )
        }
    }

/**
 * List containing declarations without all specified children.
 *
 * @param names The name(s) of the child(s) to exclude.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations without all specified child(s).
 */
fun <T : KoChildProvider> List<T>.withoutAllChildrenNamed(
    names: List<String>,
    indirectChildren: Boolean = false
): List<T> =
    withoutAllChildrenNamed(names.toSet(), indirectChildren)

/**
 * List containing declarations that have at least one child satisfying the provided predicate.
 *
 * @param indirectChildren Whether to include indirect children.
 * @param predicate A function that defines the condition to be met by a child declaration.
 * @return A list containing declarations with at least one child satisfying the predicate.
 */
fun <T : KoChildProvider> List<T>.withChild(
    indirectChildren: Boolean = false,
    predicate: (KoChildDeclaration) -> Boolean,
): List<T> =
    filter {
        it.hasChild(indirectChildren, predicate)
    }

/**
 * List containing declarations that not have child satisfying the provided predicate.
 *
 * @param indirectChildren Whether to include indirect children.
 * @param predicate A function that defines the condition to be met by a child declaration.
 * @return A list containing declarations without child satisfying the provided predicate.
 */
fun <T : KoChildProvider> List<T>.withoutChild(
    indirectChildren: Boolean = false,
    predicate: (KoChildDeclaration) -> Boolean,
): List<T> =
    filterNot {
        it.hasChild(indirectChildren, predicate)
    }

/**
 * List containing declarations that have all children satisfying the provided predicate.
 *
 * @param indirectChildren Whether to include indirect children.
 * @param predicate A function that defines the condition to be met by all child declarations.
 * @return A filtered list containing declarations with all children satisfying the predicate.
 */
fun <T : KoChildProvider> List<T>.withAllChildren(
    indirectChildren: Boolean = false,
    predicate: (KoChildDeclaration) -> Boolean,
): List<T> =
    filter {
        it.hasAllChildren(indirectChildren, predicate)
    }

/**
 * List containing declarations that have at least one child not satisfying the provided predicate.
 *
 * @param indirectChildren Whether to include indirect children.
 * @param predicate A function that defines the condition to be met by all child declarations.
 * @return A list containing declarations that have at least one child not satisfying the provided predicate.
 */
fun <T : KoChildProvider> List<T>.withoutAllChildren(
    indirectChildren: Boolean = false,
    predicate: (KoChildDeclaration) -> Boolean,
): List<T> =
    filterNot {
        it.hasAllChildren(indirectChildren, predicate)
    }

/**
 * List containing declarations with child declarations satisfying the predicate.
 *
 * @param indirectChildren Whether to include indirect children.
 * @param predicate A function that defines the condition to be met by the list of child declarations.
 * @return A list containing declarations with child declarations satisfying the predicate.
 */
fun <T : KoChildProvider> List<T>.withChildren(
    indirectChildren: Boolean = false,
    predicate: (List<KoChildDeclaration>) -> Boolean,
): List<T> =
    filter {
        predicate(it.children(indirectChildren))
    }

/**
 * List containing declarations without child declarations satisfying the predicate.
 *
 * @param indirectChildren Whether to include indirect children.
 * @param predicate A function that defines the condition to be met by the list of child declarations.
 * @return A list containing declarations without child declarations satisfying the predicate.
 */
fun <T : KoChildProvider> List<T>.withoutChildren(
    indirectChildren: Boolean = false,
    predicate: (List<KoChildDeclaration>) -> Boolean,
): List<T> = filterNot { predicate(it.children(indirectChildren)) }

/**
 * List containing declarations that have at least one child of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing child to include.
 * @param kClasses The Kotlin classes representing children to include.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations with at least one child of the specified `KClass` type.
 */
fun <T : KoChildProvider> List<T>.withChildOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectChildren: Boolean = false,
): List<T> = filter { it.hasChildOf(kClass, *kClasses, indirectChildren = indirectChildren) }

/**
 * List containing declarations that have at least one child of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing children to include.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations with at least one child of the specified `KClass` type.
 */
fun <T : KoChildProvider> List<T>.withChildOf(kClasses: Set<KClass<*>>, indirectChildren: Boolean = false): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasChildren(indirectChildren)
            else -> it.hasChildOf(
                kClasses.first(),
                *kClasses.drop(1).toTypedArray(),
                indirectChildren = indirectChildren
            )
        }
    }

/**
 * List containing declarations that have at least one child of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing children to include.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations with at least one child of the specified `KClass` type.
 */
fun <T : KoChildProvider> List<T>.withChildOf(kClasses: List<KClass<*>>, indirectChildren: Boolean = false): List<T> =
    withChildOf(kClasses.toSet(), indirectChildren)

/**
 * List containing declarations without any child of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing child to exclude.
 * @param kClasses The Kotlin classes representing children to exclude.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations without any of the specified children.
 */
fun <T : KoChildProvider> List<T>.withoutChildOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectChildren: Boolean = false,
): List<T> = filterNot { it.hasChildOf(kClass, *kClasses, indirectChildren = indirectChildren) }

/**
 * List containing declarations without any child of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing children to exclude.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations without any of the specified children.
 */
fun <T : KoChildProvider> List<T>.withoutChildOf(kClasses: Set<KClass<*>>, indirectChildren: Boolean = false): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasChildren(indirectChildren)
            else -> it.hasChildOf(
                kClasses.first(),
                *kClasses.drop(1).toTypedArray(),
                indirectChildren = indirectChildren
            )
        }
    }

/**
 * List containing declarations without any child of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing children to exclude.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations without any of the specified children.
 */
fun <T : KoChildProvider> List<T>.withoutChildOf(kClasses: List<KClass<*>>, indirectChildren: Boolean = false): List<T> =
    withoutChildOf(kClasses.toSet(), indirectChildren)

/**
 * List containing declarations that have all children of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing child to include.
 * @param kClasses The Kotlin classes representing children to include.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations that have all children of the specified `KClass` type.
 */
fun <T : KoChildProvider> List<T>.withAllChildrenOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectChildren: Boolean = false,
): List<T> = filter { it.hasAllChildrenOf(kClass, *kClasses, indirectChildren = indirectChildren) }

/**
 * List containing declarations that have all children of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing children to include.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations that have all children of the specified `KClass` type.
 */
fun <T : KoChildProvider> List<T>.withAllChildrenOf(kClasses: Set<KClass<*>>, indirectChildren: Boolean = false): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasChildren(indirectChildren)
            else -> it.hasAllChildrenOf(
                kClasses.first(),
                *kClasses.drop(1).toTypedArray(),
                indirectChildren = indirectChildren
            )
        }
    }

/**
 * List containing declarations that have all children of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing children to include.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations that have all children of the specified `KClass` type.
 */
fun <T : KoChildProvider> List<T>.withAllChildrenOf(kClasses: List<KClass<*>>, indirectChildren: Boolean = false): List<T> =
    withAllChildrenOf(kClasses.toSet(), indirectChildren)

/**
 * List containing declarations without all specified `KClass` type children.
 *
 * @param kClass The Kotlin class representing child to exclude.
 * @param kClasses The Kotlin classes representing children to exclude.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations without all specified `KClass` type children.
 */
fun <T : KoChildProvider> List<T>.withoutAllChildrenOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectChildren: Boolean = false,
): List<T> = filterNot { it.hasAllChildrenOf(kClass, *kClasses, indirectChildren = indirectChildren) }

/**
 * List containing declarations without all specified `KClass` type children.
 *
 * @param kClasses The Kotlin classes representing children to exclude.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations without all specified `KClass` type children.
 */
fun <T : KoChildProvider> List<T>.withoutAllChildrenOf(kClasses: Set<KClass<*>>, indirectChildren: Boolean = false): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasChildren(indirectChildren)
            else -> it.hasAllChildrenOf(
                kClasses.first(),
                *kClasses.drop(1).toTypedArray(),
                indirectChildren = indirectChildren
            )
        }
    }

/**
 * List containing declarations without all specified `KClass` type children.
 *
 * @param kClasses The Kotlin classes representing children to exclude.
 * @param indirectChildren Whether to include indirect children.
 * @return A list containing declarations without all specified `KClass` type children.
 */
fun <T : KoChildProvider> List<T>.withoutAllChildrenOf(kClasses: List<KClass<*>>, indirectChildren: Boolean = false): List<T> =
    withoutAllChildrenOf(kClasses.toSet(), indirectChildren)

