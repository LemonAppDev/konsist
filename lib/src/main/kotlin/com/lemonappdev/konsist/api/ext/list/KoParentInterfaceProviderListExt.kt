@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import kotlin.reflect.KClass

/**
 * List containing parent interfaces.
 */
@Deprecated("Will be removed in v0.16.0", ReplaceWith("parentInterfaces()"))
val <T : KoParentInterfaceProvider> List<T>.parentInterfaces: List<KoInterfaceDeclaration>
    get() = flatMap { it.parentInterfaces }

/**
 * List containing parent interfaces.
 *
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing parent interface declarations.
 */
fun <T : KoParentInterfaceProvider> List<T>.parentInterfaces(indirectParents: Boolean = false): List<KoInterfaceDeclaration> =
    flatMap { it.parentInterfaces(indirectParents) }

/**
 * List containing declarations with any parent interface.
 *
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations with any parent interface.
 */
fun <T : KoParentInterfaceProvider> List<T>.withParentInterfaces(indirectParents: Boolean = false): List<T> =
    filter { it.hasParentInterfaces(indirectParents) }

/**
 * List containing declarations with none parent interface.
 *
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations with no parent interface.
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutParentInterfaces(indirectParents: Boolean = false): List<T> =
    filterNot { it.hasParentInterfaces(indirectParents) }

/**
 * List containing declarations that have at least one parent interface with the specified name(s).
 *
 * @param name The name of the parent interface to include.
 * @param names The names of additional parent interfaces to include.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations with at least one of the specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withParentInterfaceNamed(
    name: String,
    vararg names: String,
    indirectParents: Boolean = false,
): List<T> = filter { it.hasParentInterfaceWithName(name, *names, indirectParents = indirectParents) }

/**
 * List containing declarations that have at least one parent interface with the specified name(s).
 *
 * @param names The names of additional parent interfaces to include.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations with at least one of the specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withParentInterfaceNamed(
    names: Set<String>,
    indirectParents: Boolean = false,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasParentInterfaces(indirectParents = indirectParents)
            else -> it.hasParentInterfaceWithName(
                names.first(),
                *names.drop(1).toTypedArray(),
                indirectParents = indirectParents
            )
        }
    }

/**
 * List containing declarations that have at least one parent interface with the specified name(s).
 *
 * @param names The names of additional parent interfaces to include.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations with at least one of the specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withParentInterfaceNamed(
    names: List<String>,
    indirectParents: Boolean = false,
): List<T> = withParentInterfaceNamed(names.toSet(), indirectParents)

/**
 * List containing declarations without any of specified parent interfaces.
 *
 * @param name The name of the parent interface to exclude.
 * @param names The names of additional parent interfaces to exclude.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations without any of specified parent interfaces.
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutParentInterfaceNamed(
    name: String,
    vararg names: String,
    indirectParents: Boolean = false,
): List<T> = filterNot { it.hasParentInterfaceWithName(name, *names, indirectParents = indirectParents) }

/**
 * List containing declarations without any of specified parent interfaces.
 *
 * @param names The names of additional parent interfaces to exclude.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations without any of specified parent interfaces.
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutParentInterfaceNamed(
    names: Set<String>,
    indirectParents: Boolean = false,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasParentInterfaces(indirectParents = indirectParents)
            else -> it.hasParentInterfaceWithName(
                names.first(),
                *names.drop(1).toTypedArray(),
                indirectParents = indirectParents
            )
        }
    }

/**
 * List containing declarations without any of specified parent interfaces.
 *
 * @param names The names of additional parent interfaces to exclude.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations without any of specified parent interfaces.
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutParentInterfaceNamed(
    names: List<String>,
    indirectParents: Boolean = false,
): List<T> = withoutParentInterfaceNamed(names.toSet(), indirectParents)

/**
 * List containing declarations that have all specified parent interfaces.
 *
 * @param name The name of the parent interface to include.
 * @param names The name(s) of the parent interface(s) to include.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations with all specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withAllParentInterfacesNamed(
    name: String,
    vararg names: String,
    indirectParents: Boolean = false,
): List<T> = filter { it.hasParentInterfacesWithAllNames(name, *names, indirectParents = indirectParents) }

/**
 * List containing declarations that have all specified parent interfaces.
 *
 * @param names The name(s) of the parent interface(s) to include.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations with all specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withAllParentInterfacesNamed(
    names: Set<String>,
    indirectParents: Boolean = false,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasParentInterfaces(indirectParents = indirectParents)
            else -> it.hasParentInterfacesWithAllNames(
                names.first(),
                *names.drop(1).toTypedArray(),
                indirectParents = indirectParents
            )
        }
    }

/**
 * List containing declarations that have all specified parent interfaces.
 *
 * @param names The name(s) of the parent interface(s) to include.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations with all specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withAllParentInterfacesNamed(
    names: List<String>,
    indirectParents: Boolean = false,
): List<T> = withAllParentInterfacesNamed(names.toSet(), indirectParents)

/**
 * List containing declarations without all specified parent interfaces.
 *
 * @param name The name of the parent interface to exclude.
 * @param names The name(s) of the parent interface(s) to exclude.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations without all specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutAllParentInterfacesNamed(
    name: String,
    vararg names: String,
    indirectParents: Boolean = false,
): List<T> = filterNot { it.hasParentInterfacesWithAllNames(name, *names, indirectParents = indirectParents) }

/**
 * List containing declarations without all specified parent interfaces.
 *
 * @param names The name(s) of the parent interface(s) to exclude.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations without all specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutAllParentInterfacesNamed(
    names: Set<String>,
    indirectParents: Boolean = false,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasParentInterfaces(indirectParents = indirectParents)
            else -> it.hasParentInterfacesWithAllNames(
                names.first(),
                *names.drop(1).toTypedArray(),
                indirectParents = indirectParents
            )
        }
    }

/**
 * List containing declarations without all specified parent interfaces.
 *
 * @param names The name(s) of the parent interface(s) to exclude.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations without all specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutAllParentInterfacesNamed(
    names: List<String>,
    indirectParents: Boolean = false,
): List<T> = withoutAllParentInterfacesNamed(names.toSet(), indirectParents)

/**
 * List containing declarations that have at least one parent interface satisfying the provided predicate.
 *
 * @param indirectParents Whether to include indirect parent interfaces.
 * @param predicate A function that defines the condition to be met by a parent interface declaration.
 * @return A list containing declarations with at least one parent interface satisfying the predicate.
 */
fun <T : KoParentInterfaceProvider> List<T>.withParentInterface(
    indirectParents: Boolean = false,
    predicate: (KoInterfaceDeclaration) -> Boolean,
): List<T> = filter { it.hasParentInterface(indirectParents, predicate) }

/**
 * List containing declarations that not have parent interface satisfying the provided predicate.
 *
 * @param indirectParents Whether to include indirect parent interfaces.
 * @param predicate A function that defines the condition to be met by a parent interface declaration.
 * @return A list containing declarations without parent interface satisfying the provided predicate.
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutParentInterface(
    indirectParents: Boolean = false,
    predicate: (KoInterfaceDeclaration) -> Boolean,
): List<T> = filterNot { it.hasParentInterface(indirectParents, predicate) }

/**
 * List containing declarations that have all parent interfaces satisfying the provided predicate.
 *
 * @param indirectParents Whether to include indirect parent interfaces.
 * @param predicate A function that defines the condition to be met by all parent interface declarations.
 * @return A filtered list containing declarations with all parent interfaces satisfying the predicate.
 */
fun <T : KoParentInterfaceProvider> List<T>.withAllParentInterfaces(
    indirectParents: Boolean = false,
    predicate: (KoInterfaceDeclaration) -> Boolean,
): List<T> = filter { it.hasAllParentInterfaces(indirectParents, predicate) }

/**
 * List containing declarations that have at least one parent interface not satisfying the provided predicate.
 *
 * @param indirectParents Whether to include indirect parent interfaces.
 * @param predicate A function that defines the condition to be met by all parent interface declarations.
 * @return A list containing declarations that have at least one parent interface not satisfying the provided predicate.
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutAllParentInterfaces(
    indirectParents: Boolean = false,
    predicate: (KoInterfaceDeclaration) -> Boolean,
): List<T> = filterNot { it.hasAllParentInterfaces(indirectParents, predicate) }

/**
 * List containing declarations with parent interface declarations satisfying the predicate.
 *
 * @param indirectParents Whether to include indirect parent interfaces.
 * @param predicate A function that defines the condition to be met by the list of parent interface declarations.
 * @return A list containing declarations with parent interface declarations satisfying the predicate.
 */
fun <T : KoParentInterfaceProvider> List<T>.withParentInterfaces(
    indirectParents: Boolean = false,
    predicate: (List<KoInterfaceDeclaration>) -> Boolean,
): List<T> = filter { predicate(it.parentInterfaces(indirectParents)) }

/**
 * List containing declarations without parent interface declarations satisfying the predicate.
 *
 * @param indirectParents Whether to include indirect parent interfaces.
 * @param predicate A function that defines the condition to be met by the list of parent interface declarations.
 * @return A list containing declarations without parent interface declarations satisfying the predicate.
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutParentInterfaces(
    indirectParents: Boolean = false,
    predicate: (List<KoInterfaceDeclaration>) -> Boolean,
): List<T> = filterNot { predicate(it.parentInterfaces(indirectParents)) }

/**
 * List containing declarations that have at least one parent interface of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing parent interface to include.
 * @param kClasses The Kotlin classes representing parent interfaces to include.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations with at least one parent interface of the specified `KClass` type.
 */
fun <T : KoParentInterfaceProvider> List<T>.withParentInterfaceOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectParents: Boolean = false,
): List<T> = filter { it.hasParentInterfaceOf(kClass, *kClasses, indirectParents = indirectParents) }

/**
 * List containing declarations that have at least one parent interface of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing parent interfaces to include.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations with at least one parent interface of the specified `KClass` type.
 */
fun <T : KoParentInterfaceProvider> List<T>.withParentInterfaceOf(
    kClasses: Set<KClass<*>>,
    indirectParents: Boolean = false,
): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasParentInterfaces(indirectParents)
            else -> it.hasParentInterfaceOf(
                kClasses.first(),
                *kClasses.drop(1).toTypedArray(),
                indirectParents = indirectParents
            )
        }
    }

/**
 * List containing declarations that have at least one parent interface of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing parent interfaces to include.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations with at least one parent interface of the specified `KClass` type.
 */
fun <T : KoParentInterfaceProvider> List<T>.withParentInterfaceOf(
    kClasses: List<KClass<*>>,
    indirectParents: Boolean = false,
): List<T> = withParentInterfaceOf(kClasses.toSet(), indirectParents)

/**
 * List containing declarations without any parent interface of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing parent interface to exclude.
 * @param kClasses The Kotlin classes representing parent interfaces to exclude.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations without any of the specified parent interfaces.
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutParentInterfaceOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectParents: Boolean = false,
): List<T> = filterNot { it.hasParentInterfaceOf(kClass, *kClasses, indirectParents = indirectParents) }

/**
 * List containing declarations without any parent interface of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing parent interfaces to exclude.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations without any of the specified parent interfaces.
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutParentInterfaceOf(
    kClasses: Set<KClass<*>>,
    indirectParents: Boolean = false,
): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasParentInterfaces(indirectParents)
            else -> it.hasParentInterfaceOf(
                kClasses.first(),
                *kClasses.drop(1).toTypedArray(),
                indirectParents = indirectParents
            )
        }
    }

/**
 * List containing declarations without any parent interface of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing parent interfaces to exclude.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations without any of the specified parent interfaces.
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutParentInterfaceOf(
    kClasses: List<KClass<*>>,
    indirectParents: Boolean = false,
): List<T> = withoutParentInterfaceOf(kClasses.toSet(), indirectParents)

/**
 * List containing declarations that have all parent interfaces of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing parent interface to include.
 * @param kClasses The Kotlin classes representing parent interfaces to include.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations that have all parent interfaces of the specified `KClass` type.
 */
fun <T : KoParentInterfaceProvider> List<T>.withAllParentInterfacesOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectParents: Boolean = false,
): List<T> = filter { it.hasAllParentInterfacesOf(kClass, *kClasses, indirectParents = indirectParents) }

/**
 * List containing declarations that have all parent interfaces of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing parent interfaces to include.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations that have all parent interfaces of the specified `KClass` type.
 */
fun <T : KoParentInterfaceProvider> List<T>.withAllParentInterfacesOf(
    kClasses: Set<KClass<*>>,
    indirectParents: Boolean = false,
): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasParentInterfaces(indirectParents)
            else -> it.hasAllParentInterfacesOf (
                kClasses.first(),
                *kClasses.drop(1).toTypedArray(),
                indirectParents = indirectParents
            )
        }
    }

/**
 * List containing declarations that have all parent interfaces of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing parent interfaces to include.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations that have all parent interfaces of the specified `KClass` type.
 */
fun <T : KoParentInterfaceProvider> List<T>.withAllParentInterfacesOf(
    kClasses: List<KClass<*>>,
    indirectParents: Boolean = false,
): List<T> = withAllParentInterfacesOf(kClasses.toSet(), indirectParents)

/**
 * List containing declarations without all specified `KClass` type parent interfaces.
 *
 * @param kClass The Kotlin class representing parent interface to exclude.
 * @param kClasses The Kotlin classes representing parent interfaces to exclude.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations without all specified `KClass` type parent interfaces.
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutAllParentInterfacesOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectParents: Boolean = false,
): List<T> = filterNot { it.hasAllParentInterfacesOf(kClass, *kClasses, indirectParents = indirectParents) }

/**
 * List containing declarations without all specified `KClass` type parent interfaces.
 *
 * @param kClasses The Kotlin classes representing parent interfaces to exclude.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations without all specified `KClass` type parent interfaces.
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutAllParentInterfacesOf(
    kClasses: Set<KClass<*>>,
    indirectParents: Boolean = false,
): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasParentInterfaces(indirectParents)
            else -> it.hasAllParentInterfacesOf (
                kClasses.first(),
                *kClasses.drop(1).toTypedArray(),
                indirectParents = indirectParents
            )
        }
    }

/**
 * List containing declarations without all specified `KClass` type parent interfaces.
 *
 * @param kClasses The Kotlin classes representing parent interfaces to exclude.
 * @param indirectParents Whether to include indirect parent interfaces.
 * @return A list containing declarations without all specified `KClass` type parent interfaces.
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutAllParentInterfacesOf(
    kClasses: List<KClass<*>>,
    indirectParents: Boolean = false,
): List<T> = withoutAllParentInterfacesOf(kClasses.toSet(), indirectParents)

/**
 * List containing declarations with all specified parent interfaces of type.
 *
 * @param name The name of the parent interface to include.
 * @param names The name(s) of the parent interface(s) to include.
 * @return A list containing declarations with all the specified parent interface(s).
 */
@Deprecated("Will be removed in v0.16.0", ReplaceWith("withAllParents()"))
fun <T : KoParentInterfaceProvider> List<T>.withAllParentInterfaces(
    name: String,
    vararg names: String,
): List<T> = filter { it.hasParentInterfaces(name, *names) }

/**
 * List containing declarations with some named parent interface.
 *
 * @param name The name of the parent interface to include.
 * @param names The names of the parent interfaces to include.
 * @return A list containing declarations with at least one of the specified parent interface(s).
 */
@Deprecated("Will be removed in v0.16.0", ReplaceWith("withSomeParents()"))
fun <T : KoParentInterfaceProvider> List<T>.withSomeParentInterfaces(
    name: String,
    vararg names: String,
): List<T> = filter { it.hasParentInterfaces(name) || names.any { name -> it.hasParentInterfaces(name) } }

/**
 * List containing declarations without all specified parent interfaces of type.
 *
 * @param name The name of the parent interface to exclude.
 * @param names The name(s) of the parent interface(s) to exclude.
 * @return A list containing declarations without all specified parent interface(s).
 */
@Deprecated("Will be removed in v0.16.0", ReplaceWith("withoutAllParents()"))
fun <T : KoParentInterfaceProvider> List<T>.withoutAllParentInterfaces(
    name: String,
    vararg names: String,
): List<T> = filterNot { it.hasParentInterfaces(name, *names) }

/**
 * List containing declarations without some named parent interface.
 *
 * @param name The name of the parent interface to exclude.
 * @param names The names of the parent interfaces to exclude.
 * @return A list containing declarations without at least one of the specified parent interface(s).
 */
@Deprecated("Will be removed in v0.16.0", ReplaceWith("withoutSomeParents()"))
fun <T : KoParentInterfaceProvider> List<T>.withoutSomeParentInterfaces(
    name: String,
    vararg names: String,
): List<T> =
    filter {
        !it.hasParentInterfaces(name) &&
                if (names.isNotEmpty()) {
                    names.any { name -> !it.hasParentInterfaces(name) }
                } else {
                    true
                }
    }

/**
 * List containing declarations with some named parent interface.
 *
 * @param kClass The Kotlin class representing the parent interface to include.
 * @param kClasses The Kotlin declarations representing the parent interfaces to include.
 * @return A list containing declarations with at least one of the specified parent interface(s).
 */
@Deprecated("Will be removed in v0.16.0", ReplaceWith("withSomeParentsOf()"))
fun <T : KoParentInterfaceProvider> List<T>.withSomeParentInterfacesOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filter {
        it.parentInterfaces.any { parent -> parent.name == kClass.simpleName } ||
                kClasses.any { kClass ->
                    it
                        .parentInterfaces
                        .any { parent -> parent.name == kClass.simpleName }
                }
    }

/**
 * List containing declarations without some named parent interface.
 *
 * @param kClass The Kotlin class representing the parent interface to exclude.
 * @param kClasses The Kotlin declarations representing the parent interfaces to exclude.
 * @return A list containing declarations without at least one of the specified parent interface(s).
 */
@Deprecated("Will be removed in v0.16.0", ReplaceWith("withoutSomeParentsOf()"))
fun <T : KoParentInterfaceProvider> List<T>.withoutSomeParentInterfacesOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filter {
        val hasNoMatchingParentInterfaces =
            if (kClasses.isNotEmpty()) {
                kClasses.any { kClass ->
                    it
                        .parentInterfaces
                        .none { parent -> parent.name == kClass.simpleName }
                }
            } else {
                true
            }

        it.parentInterfaces.none { parent -> parent.name == kClass.simpleName } &&
                hasNoMatchingParentInterfaces
    }
