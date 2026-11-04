package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoClassAndInterfaceProvider

/**
 * List containing class or/and interface declarations.
 *
 * @param includeNested Whether to include nested classes and interfaces.
 * @param includeLocal Whether to include local classes.
 * @return A list containing class or/and interface declarations.
 * @see KoClassAndInterfaceProvider.classesAndInterfaces
 */
fun <T : KoClassAndInterfaceProvider> List<T>.classesAndInterfaces(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<KoClassAndInterfaceDeclaration> = flatMap { it.classesAndInterfaces(includeNested, includeLocal) }

/**
 * List containing declarations with any class or interface.
 *
 * @param includeNested Whether to include nested classes and interfaces.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations with any class or interface.
 * @see KoClassAndInterfaceProvider.hasClassesOrInterfaces
 */
fun <T : KoClassAndInterfaceProvider> List<T>.withClassesAndInterfaces(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = filter { it.hasClassesOrInterfaces(includeNested, includeLocal) }

/**
 * List containing declarations with no classes and interfaces.
 *
 * @param includeNested Whether to include nested classes and interfaces.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations with no classes and interfaces.
 * @see KoClassAndInterfaceProvider.hasClassesOrInterfaces
 */
fun <T : KoClassAndInterfaceProvider> List<T>.withoutClassesAndInterfaces(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = filterNot { it.hasClassesOrInterfaces(includeNested, includeLocal) }

/**
 * List containing declarations that have at least one class or interface with the specified name(s).
 *
 * @param name The name of the class or interface to include.
 * @param names The names of additional classes and interfaces to include.
 * @param includeNested Whether to include nested classes and interfaces.
 * @param includeLocal Whether to include local classes.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with at least one of the specified class(es) or interface(s).
 * @see KoClassAndInterfaceProvider.hasClassOrInterfaceWithName
 */
fun <T : KoClassAndInterfaceProvider> List<T>.withClassOrInterfaceNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> = withClassOrInterfaceNamed(listOf(name, *names), includeNested, includeLocal, ignoreCase)

/**
 * List containing declarations that have at least one class or interface with the specified name(s).
 *
 * @param names The names of the classes and interfaces to include.
 * @param includeNested Whether to include nested classes and interfaces.
 * @param includeLocal Whether to include local classes.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with at least one of the specified class(es) or interface(s).
 * @see KoClassAndInterfaceProvider.hasClassOrInterfaceWithName
 */
fun <T : KoClassAndInterfaceProvider> List<T>.withClassOrInterfaceNamed(
    names: Collection<String>,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> =
    filter {
        when {
            names.isEmpty() -> {
                it.hasClassesOrInterfaces(includeNested, includeLocal)
            }

            else -> {
                it.hasClassOrInterfaceWithName(
                    names,
                    includeNested = includeNested,
                    includeLocal = includeLocal,
                    ignoreCase = ignoreCase,
                )
            }
        }
    }

/**
 * List containing declarations without any of the specified classes and interfaces.
 *
 * @param name The name of the class or interface to exclude.
 * @param names The names of additional classes and interfaces to exclude.
 * @param includeNested Whether to include nested classes and interfaces.
 * @param includeLocal Whether to include local classes.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without any of the specified classes and interfaces.
 * @see KoClassAndInterfaceProvider.hasClassOrInterfaceWithName
 */
fun <T : KoClassAndInterfaceProvider> List<T>.withoutClassOrInterfaceNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> = withoutClassOrInterfaceNamed(listOf(name, *names), includeNested, includeLocal, ignoreCase)

/**
 * List containing declarations without any of the specified classes and interfaces.
 *
 * @param names The names of the classes and interfaces to exclude.
 * @param includeNested Whether to include nested classes and interfaces.
 * @param includeLocal Whether to include local classes.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without any of the specified classes and interfaces.
 * @see KoClassAndInterfaceProvider.hasClassOrInterfaceWithName
 */
fun <T : KoClassAndInterfaceProvider> List<T>.withoutClassOrInterfaceNamed(
    names: Collection<String>,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> {
                it.hasClassesOrInterfaces(includeNested, includeLocal)
            }

            else -> {
                it.hasClassOrInterfaceWithName(
                    names,
                    includeNested = includeNested,
                    includeLocal = includeLocal,
                    ignoreCase = ignoreCase,
                )
            }
        }
    }

/**
 * List containing declarations that have all specified classes and interfaces.
 *
 * @param name The name of the class or interface to include.
 * @param names The names of additional classes and interfaces to include.
 * @param includeNested Whether to include nested classes and interfaces.
 * @param includeLocal Whether to include local classes.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with all specified class(es) and interface(s).
 * @see KoClassAndInterfaceProvider.hasClassesAndInterfacesWithAllNames
 */
fun <T : KoClassAndInterfaceProvider> List<T>.withAllClassesAndInterfacesNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> = withAllClassesAndInterfacesNamed(listOf(name, *names), includeNested, includeLocal, ignoreCase)

/**
 * List containing declarations that have all specified classes and interfaces.
 *
 * @param names The names of the classes and interfaces to include.
 * @param includeNested Whether to include nested classes and interfaces.
 * @param includeLocal Whether to include local classes.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with all specified class(es) and interface(s).
 * @see KoClassAndInterfaceProvider.hasClassesAndInterfacesWithAllNames
 */
fun <T : KoClassAndInterfaceProvider> List<T>.withAllClassesAndInterfacesNamed(
    names: Collection<String>,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> =
    filter {
        when {
            names.isEmpty() -> {
                it.hasClassesOrInterfaces(includeNested, includeLocal)
            }

            else -> {
                it.hasClassesAndInterfacesWithAllNames(
                    names,
                    includeNested = includeNested,
                    includeLocal = includeLocal,
                    ignoreCase = ignoreCase,
                )
            }
        }
    }

/**
 * List containing declarations without all specified classes and interfaces.
 *
 * @param name The name of the class or interface to exclude.
 * @param names The names of additional classes and interfaces to exclude.
 * @param includeNested Whether to include nested classes and interfaces.
 * @param includeLocal Whether to include local classes.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without all specified class(es) and interface(s).
 * @see KoClassAndInterfaceProvider.hasClassesAndInterfacesWithAllNames
 */
fun <T : KoClassAndInterfaceProvider> List<T>.withoutAllClassesAndInterfacesNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> = withoutAllClassesAndInterfacesNamed(listOf(name, *names), includeNested, includeLocal, ignoreCase)

/**
 * List containing declarations without all specified classes and interfaces.
 *
 * @param names The names of the classes and interfaces to exclude.
 * @param includeNested Whether to include nested classes and interfaces.
 * @param includeLocal Whether to include local classes.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without all specified class(es) and interface(s).
 * @see KoClassAndInterfaceProvider.hasClassesAndInterfacesWithAllNames
 */
fun <T : KoClassAndInterfaceProvider> List<T>.withoutAllClassesAndInterfacesNamed(
    names: Collection<String>,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> {
                it.hasClassesOrInterfaces(includeNested, includeLocal)
            }

            else -> {
                it.hasClassesAndInterfacesWithAllNames(
                    names,
                    includeNested = includeNested,
                    includeLocal = includeLocal,
                    ignoreCase = ignoreCase,
                )
            }
        }
    }

/**
 * List containing declarations that have at least one class or interface satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested classes and interfaces.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by a class or interface declaration.
 * @return A list containing declarations with at least one class or interface satisfying the predicate.
 * @see KoClassAndInterfaceProvider.hasClassOrInterface
 */
fun <T : KoClassAndInterfaceProvider> List<T>.withClassOrInterface(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoClassAndInterfaceDeclaration) -> Boolean,
): List<T> = filter { it.hasClassOrInterface(includeNested, includeLocal, predicate) }

/**
 * List containing declarations that do not have any class or interface satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested classes and interfaces.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by a class or interface declaration.
 * @return A list containing declarations without any class or interface satisfying the provided predicate.
 * @see KoClassAndInterfaceProvider.hasClassOrInterface
 */
fun <T : KoClassAndInterfaceProvider> List<T>.withoutClassOrInterface(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoClassAndInterfaceDeclaration) -> Boolean,
): List<T> = filterNot { it.hasClassOrInterface(includeNested, includeLocal, predicate) }

/**
 * List containing declarations that have all classes and interfaces satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested classes and interfaces.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by all class and interface declarations.
 * @return A filtered list containing declarations with all classes and interfaces satisfying the predicate.
 * @see KoClassAndInterfaceProvider.hasAllClassesAndInterfaces
 */
fun <T : KoClassAndInterfaceProvider> List<T>.withAllClassesAndInterfaces(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoClassAndInterfaceDeclaration) -> Boolean,
): List<T> =
    filter {
        it.hasAllClassesAndInterfaces(includeNested, includeLocal, predicate)
    }

/**
 * List containing declarations that have at least one class or interface not satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested classes and interfaces.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by all class and interface declarations.
 * @return A list containing declarations that have at least one class or interface not satisfying the provided predicate.
 * @see KoClassAndInterfaceProvider.hasAllClassesAndInterfaces
 */
fun <T : KoClassAndInterfaceProvider> List<T>.withoutAllClassesAndInterfaces(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoClassAndInterfaceDeclaration) -> Boolean,
): List<T> = filterNot { it.hasAllClassesAndInterfaces(includeNested, includeLocal, predicate) }

/**
 * List containing declarations with class and interface declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested classes and interfaces.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by the list of class and interface declarations.
 * @return A list containing declarations with class and interface declarations satisfying the predicate.
 * @see KoClassAndInterfaceProvider.classesAndInterfaces
 */
fun <T : KoClassAndInterfaceProvider> List<T>.withClassesAndInterfaces(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (List<KoClassAndInterfaceDeclaration>) -> Boolean,
): List<T> = filter { predicate(it.classesAndInterfaces(includeNested, includeLocal)) }

/**
 * List containing declarations without class and interface declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested classes and interfaces.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by the list of class and interface declarations.
 * @return A list containing declarations without class and interface declarations satisfying the predicate.
 * @see KoClassAndInterfaceProvider.classesAndInterfaces
 */
fun <T : KoClassAndInterfaceProvider> List<T>.withoutClassesAndInterfaces(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (List<KoClassAndInterfaceDeclaration>) -> Boolean,
): List<T> = filterNot { predicate(it.classesAndInterfaces(includeNested, includeLocal)) }
