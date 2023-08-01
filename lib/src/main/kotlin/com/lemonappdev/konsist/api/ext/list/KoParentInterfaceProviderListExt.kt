package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import kotlin.reflect.KClass

/**
 * List containing all declarations with any named parent interface.
 *
 * @return A list containing declarations with any parent interface.
 */
fun <T : KoParentInterfaceProvider> List<T>.withParentInterfaces(): List<T> = filter { it.hasParentInterfaces() }

/**
 * List containing all declarations with all specified parent interfaces of type.
 *
 * @param name The name of the parent interface to include.
 * @param names The name(s) of the parent interface(s) to include.
 * @return A list containing declarations with all the specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withAllParentInterfaces(name: String, vararg names: String): List<T> = filter {
    it.hasParentInterfaces(name, *names)
}

/**
 * List containing all declarations with some named parent interface.
 *
 * @param name The name of the parent interface to include.
 * @param names The names of the parent interfaces to include.
 * @return A list containing declarations with at least one of the specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withSomeParentInterfaces(name: String, vararg names: String): List<T> =
    filter { it.hasParentInterfaces(name) || names.any { name -> it.hasParentInterfaces(name) } }

/**
 * List containing all declarations with no named parent interface.
 *
 * @return A list containing declarations with no parent interface.
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutParentInterfaces(): List<T> = filterNot { it.hasParentInterfaces() }

/**
 * List containing all declarations without all specified parent interfaces of type.
 *
 * @param name The name of the parent interface to exclude.
 * @param names The name(s) of the parent interface(s) to exclude.
 * @return A list containing declarations without all specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutAllParentInterfaces(name: String, vararg names: String): List<T> = filterNot {
    it.hasParentInterfaces(name, *names)
}

/**
 * List containing all declarations without some named parent interface.
 *
 * @param name The name of the parent interface to exclude.
 * @param names The names of the parent interfaces to exclude.
 * @return A list containing declarations without at least one of the specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutSomeParentInterfaces(name: String, vararg names: String): List<T> = filter {
    !it.hasParentInterfaces(name) && if (names.isNotEmpty()) {
        names.any { name -> !it.hasParentInterfaces(name) }
    } else {
        true
    }
}

/**
 * List containing all declarations with named parent interface.
 *
 * @return A list containing declarations with the parent interface of the specified type.
 */
inline fun <reified T> List<KoParentInterfaceProvider>.withParentInterfaceOf(): List<KoParentInterfaceProvider> =
    withAllParentInterfacesOf(T::class)

/**
 * List containing all declarations without some named parent interface.
 *
 * @return A list containing declarations without parent interface of the specified type.
 */
inline fun <reified T> List<KoParentInterfaceProvider>.withoutParentInterfaceOf(): List<KoParentInterfaceProvider> =
    withoutAllParentInterfacesOf(T::class)

/**
 * List containing all declarations with all specified parent interfaces of type.
 *
 * @param name The Kotlin class representing the parent interface to include.
 * @param names The Kotlin declarations representing the parent interfaces to include.
 * @return A list containing declarations with the parent interfaces of the specified type(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withAllParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>): List<T> =
    filter {
        it.parentInterfaces.any { parent -> parent.name == name.simpleName } &&
            names.all { kClass ->
                it
                    .parentInterfaces
                    .any { parent -> parent.name == kClass.simpleName }
            }
    }

/**
 * List containing all declarations with some named parent interface.
 *
 * @param name The Kotlin class representing the parent interface to include.
 * @param names The Kotlin declarations representing the parent interfaces to include.
 * @return A list containing declarations with at least one of the specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withSomeParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>): List<T> =
    filter {
        it.parentInterfaces.any { parent -> parent.name == name.simpleName } ||
            names.any { kClass ->
                it
                    .parentInterfaces
                    .any { parent -> parent.name == kClass.simpleName }
            }
    }

/**
 * List containing all declarations without named parent interface.
 *
 * @param name The Kotlin class representing the parent interface to exclude.
 * @param names The Kotlin declarations representing the parent interfaces to exclude.
 * @return A list containing declarations without parent interfaces of the specified type(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutAllParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>): List<T> =
    filter {
        it.parentInterfaces.none { parent -> parent.name == name.simpleName } &&
            names.none { kClass ->
                it
                    .parentInterfaces
                    .any { parent -> parent.name == kClass.simpleName }
            }
    }

/**
 * List containing all declarations without some named parent interface.
 *
 * @param name The Kotlin class representing the parent interface to exclude.
 * @param names The Kotlin declarations representing the parent interfaces to exclude.
 * @return A list containing declarations without at least one of the specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> List<T>.withoutSomeParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>): List<T> =
    filter {
        it.parentInterfaces.none { parent -> parent.name == name.simpleName } &&
            if (names.isNotEmpty()) {
                names.any { kClass ->
                    it
                        .parentInterfaces
                        .none { parent -> parent.name == kClass.simpleName }
                }
            } else {
                true
            }
    }
