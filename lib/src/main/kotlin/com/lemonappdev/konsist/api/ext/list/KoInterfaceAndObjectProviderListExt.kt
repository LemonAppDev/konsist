package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.combined.KoInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoInterfaceAndObjectProvider

/**
 * List containing interface or/and object declarations.
 *
 * @param includeNested Whether to include nested interfaces and objects.
 * @return A list containing interface or/and objects declarations.
 */
fun <T : KoInterfaceAndObjectProvider> List<T>.interfacesAndObjects(includeNested: Boolean = true): List<KoInterfaceAndObjectDeclaration> =
    flatMap { it.interfacesAndObjects(includeNested) }

/**
 * List containing declarations with any interface or object.
 *
 * @param includeNested Whether to include nested interfaces and objects.
 * @return A list containing declarations with any interface or object.
 */
fun <T : KoInterfaceAndObjectProvider> List<T>.withInterfacesAndObjects(includeNested: Boolean = true): List<T> =
    filter { it.hasInterfacesOrObjects(includeNested) }

/**
 * List containing declarations with no interfaces and objects.
 *
 * @param includeNested Whether to include nested interfaces and objects.
 * @return A list containing declarations with no interfaces and objects.
 */
fun <T : KoInterfaceAndObjectProvider> List<T>.withoutInterfacesAndObjects(includeNested: Boolean = true): List<T> =
    filterNot { it.hasInterfacesOrObjects(includeNested) }

/**
 * List containing declarations that have at least one interface or object with the specified name(s).
 *
 * @param name The name of the interface or object to include.
 * @param names The names of additional interfaces and objects to include.
 * @param includeNested Whether to include nested interfaces and objects.
 * @return A list containing declarations with at least one of the specified interface(s) or object(s).
 */
fun <T : KoInterfaceAndObjectProvider> List<T>.withInterfaceOrObjectNamed(
    name: String,
    vararg names: String, includeNested: Boolean = true,
): List<T> = withInterfaceOrObjectNamed(listOf(name, *names), includeNested)

/**
 * List containing declarations that have at least one interface or object with the specified name(s).
 *
 * @param names The names of additional interfaces and objects to include.
 * @param includeNested Whether to include nested interfaces and objects.
 * @return A list containing declarations with at least one of the specified interface(s) or object(s).
 */
fun <T : KoInterfaceAndObjectProvider> List<T>.withInterfaceOrObjectNamed(
    names: Collection<String>, includeNested: Boolean = true,
): List<T> = filter {
    when {
        names.isEmpty() -> it.hasInterfacesOrObjects(includeNested)
        else -> it.hasInterfaceOrObjectWithName(names, includeNested = includeNested)
    }
}

/**
 * List containing declarations without any of specified interfaces and objects.
 *
 * @param name The name of the interface or object to exclude.
 * @param names The names of additional interfaces and objects to exclude.
 * @param includeNested Whether to include nested interfaces and objects.
 * @return A list containing declarations without any of specified interfaces and objects.
 */
fun <T : KoInterfaceAndObjectProvider> List<T>.withoutInterfaceOrObjectNamed(
    name: String,
    vararg names: String, includeNested: Boolean = true,
): List<T> = withoutInterfaceOrObjectNamed(listOf(name, *names), includeNested)

/**
 * List containing declarations without any of specified interfaces and objects.
 *
 * @param names The names of additional interfaces and objects to exclude.
 * @param includeNested Whether to include nested interfaces and objects.
 * @return A list containing declarations without any of specified interfaces and objects.
 */
fun <T : KoInterfaceAndObjectProvider> List<T>.withoutInterfaceOrObjectNamed(
    names: Collection<String>, includeNested: Boolean = true,
): List<T> = filterNot {
    when {
        names.isEmpty() -> it.hasInterfacesOrObjects(includeNested)
        else -> it.hasInterfaceOrObjectWithName(names, includeNested = includeNested)
    }
}

/**
 * List containing declarations that have all specified interfaces and objects.
 *
 * @param name The name of the interface or object to include.
 * @param names The name(s) of the interface(s) and object(s) to include.
 * @param includeNested Whether to include nested interfaces and objects.
 * @return A list containing declarations with all specified interface(s) and object(s).
 */
fun <T : KoInterfaceAndObjectProvider> List<T>.withAllInterfacesAndObjectsNamed(
    name: String,
    vararg names: String, includeNested: Boolean = true,
): List<T> = withAllInterfacesAndObjectsNamed(listOf(name, *names), includeNested)

/**
 * List containing declarations that have all specified interfaces and objects.
 *
 * @param names The name(s) of the interface(s) and object(s) to include.
 * @param includeNested Whether to include nested interfaces and objects.
 * @return A list containing declarations with all specified interface(s) and object(s).
 */
fun <T : KoInterfaceAndObjectProvider> List<T>.withAllInterfacesAndObjectsNamed(
    names: Collection<String>, includeNested: Boolean = true,
): List<T> = filter {
    when {
        names.isEmpty() -> it.hasInterfacesOrObjects(includeNested)
        else -> it.hasInterfacesAndObjectsWithAllNames(names, includeNested = includeNested)
    }
}

/**
 * List containing declarations without all specified interfaces and objects.
 *
 * @param name The name of the interface or object to exclude.
 * @param names The name(s) of the interface(s) and object(s) to exclude.
 * @param includeNested Whether to include nested interfaces and objects.
 * @return A list containing declarations without all specified interface(s) and object(s).
 */
fun <T : KoInterfaceAndObjectProvider> List<T>.withoutAllInterfacesAndObjectsNamed(
    name: String,
    vararg names: String, includeNested: Boolean = true,
): List<T> = withoutAllInterfacesAndObjectsNamed(listOf(name, *names), includeNested)

/**
 * List containing declarations without all specified interfaces and objects.
 *
 * @param names The name(s) of the interface(s) and object(s) to exclude.
 * @param includeNested Whether to include nested interfaces and objects.
 * @return A list containing declarations without all specified interface(s) and object(s).
 */
fun <T : KoInterfaceAndObjectProvider> List<T>.withoutAllInterfacesAndObjectsNamed(
    names: Collection<String>, includeNested: Boolean = true,
): List<T> = filterNot {
    when {
        names.isEmpty() -> it.hasInterfacesOrObjects(includeNested)
        else -> it.hasInterfacesAndObjectsWithAllNames(names, includeNested = includeNested)
    }
}

/**
 * List containing declarations that have at least one interface or object satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested interfaces and objects.
 * @param predicate A function that defines the condition to be met by an interface or object declaration.
 * @return A list containing declarations with at least one interface or object satisfying the predicate.
 */
fun <T : KoInterfaceAndObjectProvider> List<T>.withInterfaceOrObject(
    includeNested: Boolean = true,
    predicate: (KoInterfaceAndObjectDeclaration) -> Boolean,
): List<T> =
    filter { it.hasInterfaceOrObject(includeNested, predicate) }

/**
 * List containing declarations that not have interface or object satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested interfaces and objects.
 * @param predicate A function that defines the condition to be met by an interface or object declaration.
 * @return A list containing declarations without interface or object satisfying the provided predicate.
 */
fun <T : KoInterfaceAndObjectProvider> List<T>.withoutInterfaceOrObject(
    includeNested: Boolean = true,
    predicate: (KoInterfaceAndObjectDeclaration) -> Boolean,
): List<T> =
    filterNot { it.hasInterfaceOrObject(includeNested, predicate) }

/**
 * List containing declarations that have all interfaces and objects satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested interfaces and objects.
 * @param predicate A function that defines the condition to be met by all interface and objects declarations.
 * @return A filtered list containing declarations with all interfaces and objects satisfying the predicate.
 */
fun <T : KoInterfaceAndObjectProvider> List<T>.withAllInterfacesAndObjects(
    includeNested: Boolean = true,
    predicate: (KoInterfaceAndObjectDeclaration) -> Boolean,
): List<T> =
    filter {
        it.hasAllInterfacesAndObjects(includeNested, predicate)
    }

/**
 * List containing declarations that have at least one interface or object not satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested interfaces and objects.
 * @param predicate A function that defines the condition to be met by all interface or objects declarations.
 * @return A list containing declarations that have at least one interface or object not satisfying the provided predicate.
 */
fun <T : KoInterfaceAndObjectProvider> List<T>.withoutAllInterfacesAndObjects(
    includeNested: Boolean = true,
    predicate: (KoInterfaceAndObjectDeclaration) -> Boolean,
): List<T> =
    filterNot { it.hasAllInterfacesAndObjects(includeNested, predicate) }

/**
 * List containing declarations with interfaces and objects declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested interfaces and objects.
 * @param predicate A function that defines the condition to be met by the list of interfaces and objects declarations.
 * @return A list containing declarations with interfaces and objects declarations satisfying the predicate.
 */
fun <T : KoInterfaceAndObjectProvider> List<T>.withInterfacesAndObjects(
    includeNested: Boolean = true,
    predicate: (List<KoInterfaceAndObjectDeclaration>) -> Boolean,
): List<T> =
    filter { predicate(it.interfacesAndObjects(includeNested)) }

/**
 * List containing declarations without interfaces and objects declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested interfaces and objects.
 * @param predicate A function that defines the condition to be met by the list of interfaces and objects declarations.
 * @return A list containing declarations without interfaces and objects declarations satisfying the predicate.
 */
fun <T : KoInterfaceAndObjectProvider> List<T>.withoutInterfacesAndObjects(
    includeNested: Boolean = true,
    predicate: (List<KoInterfaceAndObjectDeclaration>) -> Boolean,
): List<T> =
    filterNot { predicate(it.interfacesAndObjects(includeNested)) }
