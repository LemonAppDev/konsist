package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoInterfaceProvider

/**
 * List containing interface declarations.
 *
 * @param includeNested Whether to include nested interfaces.
 * @return A list containing interface declarations.
 */
fun <T : KoInterfaceProvider> List<T>.interfaces(includeNested: Boolean = true): List<KoInterfaceDeclaration> =
    flatMap { it.interfaces(includeNested) }

/**
 * List containing declarations with any interface.
 *
 * @param includeNested Whether to include nested interfaces.
 * @return A list containing declarations with any interface.
 */
fun <T : KoInterfaceProvider> List<T>.withInterfaces(includeNested: Boolean = true): List<T> = filter { it.hasInterfaces(includeNested) }

/**
 * List containing declarations with no interfaces.
 *
 * @param includeNested Whether to include nested interfaces.
 * @return A list containing declarations with no interfaces.
 */
fun <T : KoInterfaceProvider> List<T>.withoutInterfaces(includeNested: Boolean = true): List<T> =
    filterNot { it.hasInterfaces(includeNested) }

/**
 * List containing declarations that have at least one interface with the specified name(s).
 *
 * @param name The name of the interface to include.
 * @param names The names of additional interfaces to include.
 * @param includeNested Whether to include nested interfaces.
 * @return A list containing declarations with at least one of the specified interface(s).
 */
fun <T : KoInterfaceProvider> List<T>.withInterfaceNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
): List<T> =
    filter {
        it.hasInterfaceWithName(name, *names, includeNested = includeNested)
    }

/**
 * List containing declarations that have at least one interface with the specified name(s).
 *
 * @param names The names of additional interfaces to include.
 * @param includeNested Whether to include nested interfaces.
 * @return A list containing declarations with at least one of the specified interface(s).
 */
fun <T : KoInterfaceProvider> List<T>.withInterfaceNamed(
    names: Set<String>,
    includeNested: Boolean = true,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasInterfaces(includeNested)
            else -> it.hasInterfaceWithName(names.first(), *names.drop(1).toTypedArray(), includeNested = includeNested)
        }
    }

/**
 * List containing declarations that have at least one interface with the specified name(s).
 *
 * @param names The names of additional interfaces to include.
 * @param includeNested Whether to include nested interfaces.
 * @return A list containing declarations with at least one of the specified interface(s).
 */
fun <T : KoInterfaceProvider> List<T>.withInterfaceNamed(
    names: List<String>,
    includeNested: Boolean = true,
): List<T> = withInterfaceNamed(names.toSet(), includeNested)

/**
 * List containing declarations without any of specified interfaces.
 *
 * @param name The name of the interface to exclude.
 * @param names The names of additional interfaces to exclude.
 * @param includeNested Whether to include nested interfaces.
 * @return A list containing declarations without any of specified interfaces.
 */
fun <T : KoInterfaceProvider> List<T>.withoutInterfaceNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
): List<T> =
    filterNot {
        it.hasInterfaceWithName(name, *names, includeNested = includeNested)
    }

/**
 * List containing declarations without any of specified interfaces.
 *
 * @param names The names of additional interfaces to exclude.
 * @param includeNested Whether to include nested interfaces.
 * @return A list containing declarations without any of specified interfaces.
 */
fun <T : KoInterfaceProvider> List<T>.withoutInterfaceNamed(
    names: Set<String>,
    includeNested: Boolean = true,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasInterfaces(includeNested)
            else -> it.hasInterfaceWithName(names.first(), *names.drop(1).toTypedArray(), includeNested = includeNested)
        }
    }

/**
 * List containing declarations without any of specified interfaces.
 *
 * @param names The names of additional interfaces to exclude.
 * @param includeNested Whether to include nested interfaces.
 * @return A list containing declarations without any of specified interfaces.
 */
fun <T : KoInterfaceProvider> List<T>.withoutInterfaceNamed(
    names: List<String>,
    includeNested: Boolean = true,
): List<T> = withoutInterfaceNamed(names.toSet(), includeNested)

/**
 * List containing declarations that have all specified interfaces.
 *
 * @param name The name of the interface to include.
 * @param names The name(s) of the interface(s) to include.
 * @param includeNested Whether to include nested interfaces.
 * @return A list containing declarations with all specified interface(s).
 */
fun <T : KoInterfaceProvider> List<T>.withAllInterfacesNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
): List<T> =
    filter {
        it.hasInterfacesWithAllNames(name, *names, includeNested = includeNested)
    }

/**
 * List containing declarations that have all specified interfaces.
 *
 * @param names The name(s) of the interface(s) to include.
 * @param includeNested Whether to include nested interfaces.
 * @return A list containing declarations with all specified interface(s).
 */
fun <T : KoInterfaceProvider> List<T>.withAllInterfacesNamed(
    names: Set<String>,
    includeNested: Boolean = true,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasInterfaces(includeNested)
            else ->
                it.hasInterfacesWithAllNames(
                    names.first(),
                    *names.drop(1).toTypedArray(),
                    includeNested = includeNested,
                )
        }
    }

/**
 * List containing declarations that have all specified interfaces.
 *
 * @param names The name(s) of the interface(s) to include.
 * @param includeNested Whether to include nested interfaces.
 * @return A list containing declarations with all specified interface(s).
 */
fun <T : KoInterfaceProvider> List<T>.withAllInterfacesNamed(
    names: List<String>,
    includeNested: Boolean = true,
): List<T> = withAllInterfacesNamed(names.toSet(), includeNested)

/**
 * List containing declarations without all specified interfaces.
 *
 * @param name The name of the interface to exclude.
 * @param names The name(s) of the interface(s) to exclude.
 * @param includeNested Whether to include nested interfaces.
 * @return A list containing declarations without all specified interface(s).
 */
fun <T : KoInterfaceProvider> List<T>.withoutAllInterfacesNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
): List<T> =
    filterNot {
        it.hasInterfacesWithAllNames(name, *names, includeNested = includeNested)
    }

/**
 * List containing declarations without all specified interfaces.
 *
 * @param names The name(s) of the interface(s) to exclude.
 * @param includeNested Whether to include nested interfaces.
 * @return A list containing declarations without all specified interface(s).
 */
fun <T : KoInterfaceProvider> List<T>.withoutAllInterfacesNamed(
    names: Set<String>,
    includeNested: Boolean = true,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasInterfaces(includeNested)
            else ->
                it.hasInterfacesWithAllNames(
                    names.first(),
                    *names.drop(1).toTypedArray(),
                    includeNested = includeNested,
                )
        }
    }

/**
 * List containing declarations without all specified interfaces.
 *
 * @param names The name(s) of the interface(s) to exclude.
 * @param includeNested Whether to include nested interfaces.
 * @return A list containing declarations without all specified interface(s).
 */
fun <T : KoInterfaceProvider> List<T>.withoutAllInterfacesNamed(
    names: List<String>,
    includeNested: Boolean = true,
): List<T> = withoutAllInterfacesNamed(names.toSet(), includeNested)

/**
 * List containing declarations that have at least one interface satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested interfaces.
 * @param predicate A function that defines the condition to be met by a interface declaration.
 * @return A list containing declarations with at least one interface satisfying the predicate.
 */
fun <T : KoInterfaceProvider> List<T>.withInterface(
    includeNested: Boolean = true,
    predicate: (KoInterfaceDeclaration) -> Boolean,
): List<T> =
    filter {
        it.hasInterface(includeNested, predicate)
    }

/**
 * List containing declarations that not have interface satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested interfaces.
 * @param predicate A function that defines the condition to be met by a interface declaration.
 * @return A list containing declarations without interface satisfying the provided predicate.
 */
fun <T : KoInterfaceProvider> List<T>.withoutInterface(
    includeNested: Boolean = true,
    predicate: (KoInterfaceDeclaration) -> Boolean,
): List<T> = filterNot { it.hasInterface(includeNested, predicate) }

/**
 * List containing declarations that have all interfaces satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested interfaces.
 * @param predicate A function that defines the condition to be met by all interface declarations.
 * @return A filtered list containing declarations with all interfaces satisfying the predicate.
 */
fun <T : KoInterfaceProvider> List<T>.withAllInterfaces(
    includeNested: Boolean = true,
    predicate: (KoInterfaceDeclaration) -> Boolean,
): List<T> =
    filter {
        it.hasAllInterfaces(includeNested, predicate)
    }

/**
 * List containing declarations that have at least one interface not satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested interfaces.
 * @param predicate A function that defines the condition to be met by all interface declarations.
 * @return A list containing declarations that have at least one interface not satisfying the provided predicate.
 */
fun <T : KoInterfaceProvider> List<T>.withoutAllInterfaces(
    includeNested: Boolean = true,
    predicate: (KoInterfaceDeclaration) -> Boolean,
): List<T> = filterNot { it.hasAllInterfaces(includeNested, predicate) }

/**
 * List containing declarations with interface declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested interfaces.
 * @param predicate A function that defines the condition to be met by the list of interface declarations.
 * @return A list containing declarations with interface declarations satisfying the predicate.
 */
fun <T : KoInterfaceProvider> List<T>.withInterfaces(
    includeNested: Boolean = true,
    predicate: (List<KoInterfaceDeclaration>) -> Boolean,
): List<T> = filter { predicate(it.interfaces(includeNested)) }

/**
 * List containing declarations without interface declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested interfaces.
 * @param predicate A function that defines the condition to be met by the list of interface declarations.
 * @return A list containing declarations without interface declarations satisfying the predicate.
 */
fun <T : KoInterfaceProvider> List<T>.withoutInterfaces(
    includeNested: Boolean = true,
    predicate: (List<KoInterfaceDeclaration>) -> Boolean,
): List<T> = filterNot { predicate(it.interfaces(includeNested)) }
