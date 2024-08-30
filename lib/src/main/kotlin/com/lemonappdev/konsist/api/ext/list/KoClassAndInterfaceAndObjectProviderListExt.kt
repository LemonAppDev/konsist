package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoClassAndInterfaceAndObjectProvider

/**
 * List containing class, interface or/and object declarations.
 *
 * @param includeNested Whether to include nested classes, interfaces and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing class, interface or/and object declarations.
 */
fun <T : KoClassAndInterfaceAndObjectProvider> List<T>.classesAndInterfacesAndObjects(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<KoClassAndInterfaceAndObjectDeclaration> = flatMap { it.classesAndInterfacesAndObjects(includeNested, includeLocal) }

/**
 * List containing declarations with any class, interface or object.
 *
 * @param includeNested Whether to include nested classes, interfaces and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations with any class, interface or object.
 */
fun <T : KoClassAndInterfaceAndObjectProvider> List<T>.withClassesAndInterfacesAndObjects(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = filter { it.hasClassesOrInterfacesOrObjects(includeNested, includeLocal) }

/**
 * List containing declarations with no classes, interfaces and objects.
 *
 * @param includeNested Whether to include nested classes, interfaces and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations with no classes, interfaces and objects.
 */
fun <T : KoClassAndInterfaceAndObjectProvider> List<T>.withoutClassesAndInterfacesAndObjects(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = filterNot { it.hasClassesOrInterfacesOrObjects(includeNested, includeLocal) }

/**
 * List containing declarations that have at least one class, interface or object with the specified name(s).
 *
 * @param name The name of the class, interface or object to include.
 * @param names The names of additional classes, interfaces and objects to include.
 * @param includeNested Whether to include nested classes, interfaces and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations with at least one of the specified class(es), interface(s) or object(s).
 */
fun <T : KoClassAndInterfaceAndObjectProvider> List<T>.withClassOrInterfaceOrObjectNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = withClassOrInterfaceOrObjectNamed(listOf(name, *names), includeNested, includeLocal)

/**
 * List containing declarations that have at least one class, interface or object with the specified name(s).
 *
 * @param names The names of additional classes, interfaces and objects to include.
 * @param includeNested Whether to include nested classes, interfaces and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations with at least one of the specified class(es), interface(s) or object(s).
 */
fun <T : KoClassAndInterfaceAndObjectProvider> List<T>.withClassOrInterfaceOrObjectNamed(
    names: Collection<String>,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasClassesOrInterfacesOrObjects(includeNested, includeLocal)
            else -> it.hasClassOrInterfaceOrObjectWithName(names, includeNested = includeNested, includeLocal = includeLocal)
        }
    }

/**
 * List containing declarations without any of specified classes, interfaces and objects.
 *
 * @param name The name of the class, interface or object to exclude.
 * @param names The names of additional classes, interfaces and objects to exclude.
 * @param includeNested Whether to include nested classes, interfaces and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations without any of specified classes, interfaces and objects.
 */
fun <T : KoClassAndInterfaceAndObjectProvider> List<T>.withoutClassOrInterfaceOrObjectNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = withoutClassOrInterfaceOrObjectNamed(listOf(name, *names), includeNested, includeLocal)

/**
 * List containing declarations without any of specified classes, interfaces and objects.
 *
 * @param names The names of additional classes, interfaces and objects to exclude.
 * @param includeNested Whether to include nested classes, interfaces and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations without any of specified classes, interfaces and objects.
 */
fun <T : KoClassAndInterfaceAndObjectProvider> List<T>.withoutClassOrInterfaceOrObjectNamed(
    names: Collection<String>,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasClassesOrInterfacesOrObjects(includeNested, includeLocal)
            else -> it.hasClassOrInterfaceOrObjectWithName(names, includeNested = includeNested, includeLocal = includeLocal)
        }
    }

/**
 * List containing declarations that have all specified classes, interfaces and objects.
 *
 * @param name The name of the class, interface or object to include.
 * @param names The name(s) of the class(es), interface(s) and object(s) to include.
 * @param includeNested Whether to include nested classes, interfaces and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations with all specified class(es), interface(s) and object(s).
 */
fun <T : KoClassAndInterfaceAndObjectProvider> List<T>.withAllClassesAndInterfacesAndObjectsNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = withAllClassesAndInterfacesAndObjectsNamed(listOf(name, *names), includeNested, includeLocal)

/**
 * List containing declarations that have all specified classes, interfaces and objects.
 *
 * @param names The name(s) of the class(es), interface(s) and object(s) to include.
 * @param includeNested Whether to include nested classes, interfaces and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations with all specified class(es), interface(s) and object(s).
 */
fun <T : KoClassAndInterfaceAndObjectProvider> List<T>.withAllClassesAndInterfacesAndObjectsNamed(
    names: Collection<String>,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasClassesOrInterfacesOrObjects(includeNested, includeLocal)
            else -> it.hasClassesAndInterfacesAndObjectsWithAllNames(names, includeNested = includeNested, includeLocal = includeLocal)
        }
    }

/**
 * List containing declarations without all specified classes, interfaces and objects.
 *
 * @param name The name of the class, interface or object to exclude.
 * @param names The name(s) of the class(es), interface(s) and object(s) to exclude.
 * @param includeNested Whether to include nested classes, interfaces and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations without all specified class(es), interface(s) and object(s).
 */
fun <T : KoClassAndInterfaceAndObjectProvider> List<T>.withoutAllClassesAndInterfacesAndObjectsNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = withoutAllClassesAndInterfacesAndObjectsNamed(listOf(name, *names), includeNested, includeLocal)

/**
 * List containing declarations without all specified classes, interfaces and objects.
 *
 * @param names The name(s) of the class(es), interface(s) and object(s) to exclude.
 * @param includeNested Whether to include nested classes, interfaces and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations without all specified class(es), interface(s) and object(s).
 */
fun <T : KoClassAndInterfaceAndObjectProvider> List<T>.withoutAllClassesAndInterfacesAndObjectsNamed(
    names: Collection<String>,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasClassesOrInterfacesOrObjects(includeNested, includeLocal)
            else -> it.hasClassesAndInterfacesAndObjectsWithAllNames(names, includeNested = includeNested, includeLocal = includeLocal)
        }
    }

/**
 * List containing declarations that have at least one class, interface or object satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested classes, interfaces and objects.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by a class, interface or object declaration.
 * @return A list containing declarations with at least one class, interface or object satisfying the predicate.
 */
fun <T : KoClassAndInterfaceAndObjectProvider> List<T>.withClassOrInterfaceOrObject(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoClassAndInterfaceAndObjectDeclaration) -> Boolean,
): List<T> = filter { it.hasClassOrInterfaceOrObject(includeNested, includeLocal, predicate) }

/**
 * List containing declarations that not have class, interface or object satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested classes, interfaces and objects.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by a class, interface or object declaration.
 * @return A list containing declarations without class, interface or object satisfying the provided predicate.
 */
fun <T : KoClassAndInterfaceAndObjectProvider> List<T>.withoutClassOrInterfaceOrObject(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoClassAndInterfaceAndObjectDeclaration) -> Boolean,
): List<T> = filterNot { it.hasClassOrInterfaceOrObject(includeNested, includeLocal, predicate) }

/**
 * List containing declarations that have all classes, interfaces and objects satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested classes, interfaces and objects.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by all class, interfaces and objects declarations.
 * @return A filtered list containing declarations with all classes, interfaces and objects satisfying the predicate.
 */
fun <T : KoClassAndInterfaceAndObjectProvider> List<T>.withAllClassesAndInterfacesAndObjects(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoClassAndInterfaceAndObjectDeclaration) -> Boolean,
): List<T> =
    filter {
        it.hasAllClassesAndInterfacesAndObjects(includeNested, includeLocal, predicate)
    }

/**
 * List containing declarations that have at least one class, interface or object not satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested classes, interfaces and objects.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by all class, interfaces and objects declarations.
 * @return A list containing declarations that have at least one class, interface or object not satisfying the provided predicate.
 */
fun <T : KoClassAndInterfaceAndObjectProvider> List<T>.withoutAllClassesAndInterfacesAndObjects(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoClassAndInterfaceAndObjectDeclaration) -> Boolean,
): List<T> = filterNot { it.hasAllClassesAndInterfacesAndObjects(includeNested, includeLocal, predicate) }

/**
 * List containing declarations with classes, interfaces and objects declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested classes, interfaces and objects.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by the list of classes, interfaces and objects declarations.
 * @return A list containing declarations with classes, interfaces and objects declarations satisfying the predicate.
 */
fun <T : KoClassAndInterfaceAndObjectProvider> List<T>.withClassesAndInterfacesAndObjects(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (List<KoClassAndInterfaceAndObjectDeclaration>) -> Boolean,
): List<T> = filter { predicate(it.classesAndInterfacesAndObjects(includeNested, includeLocal)) }

/**
 * List containing declarations without classes, interfaces and objects declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested classes, interfaces and objects.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by the list of classes, interfaces and objects declarations.
 * @return A list containing declarations without classes, interfaces and objects declarations satisfying the predicate.
 */
fun <T : KoClassAndInterfaceAndObjectProvider> List<T>.withoutClassesAndInterfacesAndObjects(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (List<KoClassAndInterfaceAndObjectDeclaration>) -> Boolean,
): List<T> = filterNot { predicate(it.classesAndInterfacesAndObjects(includeNested, includeLocal)) }
