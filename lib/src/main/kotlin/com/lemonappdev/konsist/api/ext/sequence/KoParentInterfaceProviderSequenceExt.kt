package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import kotlin.reflect.KClass

/**
 * Sequence containing all declarations with any named parent interface.
 *
 * @return A sequence containing declarations with any parent interface.
 */
fun <T : KoParentInterfaceProvider> Sequence<T>.withParentInterfaces(): Sequence<T> = filter { it.hasParentInterfaces() }

/**
 * Sequence containing all declarations with all specified parent interfaces of type.
 *
 * @param name The name of the parent interface to include.
 * @param names The name(s) of the parent interface(s) to include.
 * @return A sequence containing declarations with all the specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> Sequence<T>.withAllParentInterfaces(name: String, vararg names: String): Sequence<T> = filter {
    it.hasParentInterfaces(name, *names)
}

/**
 * Sequence containing all declarations with some named parent interface.
 *
 * @param name The name of the parent interface to include.
 * @param names The names of the parent interfaces to include.
 * @return A sequence containing declarations with at least one of the specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> Sequence<T>.withSomeParentInterfaces(name: String, vararg names: String): Sequence<T> =
    filter { it.hasParentInterfaces(name) || names.any { name -> it.hasParentInterfaces(name) } }

/**
 * Sequence containing all declarations with no named parent interface.
 *
 * @return A sequence containing declarations with no parent interface.
 */
fun <T : KoParentInterfaceProvider> Sequence<T>.withoutParentInterfaces(): Sequence<T> = filterNot { it.hasParentInterfaces() }

/**
 * Sequence containing all declarations without all specified parent interfaces of type.
 *
 * @param name The name of the parent interface to exclude.
 * @param names The name(s) of the parent interface(s) to exclude.
 * @return A sequence containing declarations without all specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> Sequence<T>.withoutAllParentInterfaces(name: String, vararg names: String): Sequence<T> = filterNot {
    it.hasParentInterfaces(name, *names)
}

/**
 * Sequence containing all declarations without some named parent interface.
 *
 * @param name The name of the parent interface to exclude.
 * @param names The names of the parent interfaces to exclude.
 * @return A sequence containing declarations without at least one of the specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> Sequence<T>.withoutSomeParentInterfaces(name: String, vararg names: String): Sequence<T> = filter {
    !it.hasParentInterfaces(name) && if (names.isNotEmpty()) {
        names.any { name -> !it.hasParentInterfaces(name) }
    } else {
        true
    }
}

/**
 * Sequence containing all declarations with named parent interface.
 *
 * @return A sequence containing declarations with the parent interface of the specified type.
 */
inline fun <reified T> Sequence<KoParentInterfaceProvider>.withParentInterfaceOf(): Sequence<KoParentInterfaceProvider> = filter {
    it
        .parentInterfaces
        .any { parent -> parent.name == T::class.simpleName }
}

/**
 * Sequence containing all declarations without some named parent interface.
 *
 * @return A sequence containing declarations without parent interface of the specified type.
 */
inline fun <reified T> Sequence<KoParentInterfaceProvider>.withoutParentInterfaceOf(): Sequence<KoParentInterfaceProvider> =
    this - withParentInterfaceOf<T>().toSet()

/**
 * Sequence containing all declarations with all specified parent interfaces of type.
 *
 * @param name The Kotlin class representing the parent interface to include.
 * @param names The Kotlin declarations representing the parent interfaces to include.
 * @return A sequence containing declarations with the parent interfaces of the specified type(s).
 */
fun <T : KoParentInterfaceProvider> Sequence<T>.withAllParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>): Sequence<T> =
    filter {
        it.parentInterfaces.any { parent -> parent.name == name.simpleName } &&
            names.all { kClass ->
                it
                    .parentInterfaces
                    .any { parent -> parent.name == kClass.simpleName }
            }
    }

/**
 * Sequence containing all declarations with some named parent interface.
 *
 * @param name The Kotlin class representing the parent interface to include.
 * @param names The Kotlin declarations representing the parent interfaces to include.
 * @return A sequence containing declarations with at least one of the specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> Sequence<T>.withSomeParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>): Sequence<T> =
    filter {
        it.parentInterfaces.any { parent -> parent.name == name.simpleName } ||
            names.any { kClass ->
                it
                    .parentInterfaces
                    .any { parent -> parent.name == kClass.simpleName }
            }
    }

/**
 * Sequence containing all declarations without named parent interface.
 *
 * @param name The Kotlin class representing the parent interface to exclude.
 * @param names The Kotlin declarations representing the parent interfaces to exclude.
 * @return A sequence containing declarations without parent interfaces of the specified type(s).
 */
fun <T : KoParentInterfaceProvider> Sequence<T>.withoutAllParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>): Sequence<T> =
    filter {
        it.parentInterfaces.none { parent -> parent.name == name.simpleName } &&
            names.none { kClass ->
                it
                    .parentInterfaces
                    .any { parent -> parent.name == kClass.simpleName }
            }
    }

/**
 * Sequence containing all declarations without some named parent interface.
 *
 * @param name The Kotlin class representing the parent interface to exclude.
 * @param names The Kotlin declarations representing the parent interfaces to exclude.
 * @return A sequence containing declarations without at least one of the specified parent interface(s).
 */
fun <T : KoParentInterfaceProvider> Sequence<T>.withoutSomeParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>): Sequence<T> =
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
