package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import kotlin.reflect.KClass

/**
 * List containing elements with any named parent interface.
 *
 * @return A list containing elements with any parent interface.
 */
fun <T : KoParentInterfaceProvider> List<T>.withParentInterfaces(): List<T> = filter { it.hasParentInterfaces() }

/**
 * List containing elements with all specified parent interfaces of type.
 *
 * @param name The name of the parent interface to include.
 * @param names The name(s) of the parent interface(s) to include.
 * @return A list containing elements with all the specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withAllParentInterfaces(name: String, vararg names: String): List<T> = filter {
    it.hasParentInterfaces(name, *names)
}

/**
 * List containing elements with some named parent interface.
 *
 * @param name The name of the parent interface to include.
 * @param names The names of the parent interfaces to include.
 * @return A list containing elements with at least one of the specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withSomeParentInterfaces(name: String, vararg names: String): List<T> =
    filter { it.hasParentInterfaces(name) || names.any { name -> it.hasParentInterfaces(name) } }

/**
 * List containing elements with no named parent interface.
 *
 * @return A list containing elements with no parent interface.
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutParentInterfaces(): List<T> = filterNot { it.hasParentInterfaces() }

/**
 * List containing elements without all specified parent interfaces of type.
 *
 * @param name The name of the parent interface to exclude.
 * @param names The name(s) of the parent interface(s) to exclude.
 * @return A list containing elements without all specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutAllParentInterfaces(name: String, vararg names: String): List<T> = filterNot {
    it.hasParentInterfaces(name, *names)
}

/**
 * List containing elements without some named parent interface.
 *
 * @param name The name of the parent interface to exclude.
 * @param names The names of the parent interfaces to exclude.
 * @return A list containing elements without at least one of the specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutSomeParentInterfaces(name: String, vararg names: String): List<T> = filter {
    !it.hasParentInterfaces(name) && if (names.isNotEmpty()) {
        names.any { name -> !it.hasParentInterfaces(name) }
    } else {
        true
    }
}

/**
 * List containing elements with all specified parent interfaces of type.
 *
 * @param kClass The Kotlin class representing the parent interface to include.
 * @param kClasses The Kotlin declarations representing the parent interfaces to include.
 * @return A list containing elements with the parent interfaces of the specified type(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withAllParentInterfacesOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.parentInterfaces.any { parent -> parent.name == kClass.simpleName } &&
            kClasses.all { kClass ->
                it
                    .parentInterfaces
                    .any { parent -> parent.name == kClass.simpleName }
            }
    }

/**
 * List containing elements with some named parent interface.
 *
 * @param kClass The Kotlin class representing the parent interface to include.
 * @param kClasses The Kotlin declarations representing the parent interfaces to include.
 * @return A list containing elements with at least one of the specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withSomeParentInterfacesOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.parentInterfaces.any { parent -> parent.name == kClass.simpleName } ||
            kClasses.any { kClass ->
                it
                    .parentInterfaces
                    .any { parent -> parent.name == kClass.simpleName }
            }
    }

/**
 * List containing elements without named parent interface.
 *
 * @param kClass The Kotlin class representing the parent interface to exclude.
 * @param kClasses The Kotlin declarations representing the parent interfaces to exclude.
 * @return A list containing elements without parent interfaces of the specified type(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutAllParentInterfacesOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.parentInterfaces.none { parent -> parent.name == kClass.simpleName } &&
            kClasses.none { kClass ->
                it
                    .parentInterfaces
                    .any { parent -> parent.name == kClass.simpleName }
            }
    }

/**
 * List containing elements without some named parent interface.
 *
 * @param kClass The Kotlin class representing the parent interface to exclude.
 * @param kClasses The Kotlin declarations representing the parent interfaces to exclude.
 * @return A list containing elements without at least one of the specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutSomeParentInterfacesOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.parentInterfaces.none { parent -> parent.name == kClass.simpleName } &&
            if (kClasses.isNotEmpty()) {
                kClasses.any { kClass ->
                    it
                        .parentInterfaces
                        .none { parent -> parent.name == kClass.simpleName }
                }
            } else {
                true
            }
    }
