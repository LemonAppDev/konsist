package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoParentDeclarationProvider
import kotlin.reflect.KClass

/**
 * Sequence containing all declarations with class or interface parent.
 *
 * @return A sequence containing declarations with class or interface parent.
 */
fun <T : KoParentDeclarationProvider> Sequence<T>.withParentDeclarations(): Sequence<T> = filter { it.hasParentDeclarations() }

/**
 * Sequence containing all declarations with all specified parent declarations.
 *
 * @param name The name of the parent declaration to include.
 * @param names The name(s) of the parent declaration(s) to include.
 * @return A sequence containing declarations with all specified parent declaration(s).
 */
fun <T : KoParentDeclarationProvider> Sequence<T>.withAllParentDeclarations(name: String, vararg names: String): Sequence<T> = filter {
    it.hasParentDeclarations(name, *names)
}

/**
 * Sequence containing all declarations with some parent declarations.
 *
 * @param name The name of the parent declaration to include.
 * @param names The names of the parent declarations to include.
 * @return A sequence containing declarations with at least one of the specified parent declaration(s).
 */
fun <T : KoParentDeclarationProvider> Sequence<T>.withSomeParentDeclarations(name: String, vararg names: String): Sequence<T> = filter {
    it.hasParentDeclarations(name) || names.any { name -> it.hasParentDeclarations(name) }
}

/**
 * Sequence containing all declarations with no parent declaration - class does not extend any class and does not implement any interface.
 *
 * @return A sequence containing declarations with no parent declaration - class does not extend any class and does not implement any
 * interface.
 */
fun <T : KoParentDeclarationProvider> Sequence<T>.withoutParentDeclarations(): Sequence<T> = filterNot { it.hasParentDeclarations() }

/**
 * Sequence containing all declarations without all specified parent declarations.
 *
 * @param name The name of the parent declaration to exclude.
 * @param names The name(s) of the parent declaration(s) to exclude.
 * @return A sequence containing declarations without all specified parent declaration(s).
 */
fun <T : KoParentDeclarationProvider> Sequence<T>.withoutAllParentDeclarations(name: String, vararg names: String): Sequence<T> = filter {
    !it.hasParentDeclarations(name, *names)
}

/**
 * Sequence containing all declarations without some parent declarations represented by name.
 *
 * @param name The name of the parent declaration to exclude.
 * @param names The names of the parent declarations to exclude.
 * @return A sequence containing declarations without at least one of the specified parent declaration(s).
 */
fun <T : KoParentDeclarationProvider> Sequence<T>.withoutSomeParentDeclarations(name: String, vararg names: String): Sequence<T> = filter {
    !it.hasParentDeclarations(name) && if (names.isNotEmpty()) {
        names.any { name -> !it.hasParentDeclarations(name) }
    } else {
        true
    }
}

/**
 * Sequence containing all declarations with named parent declarations.
 *
 * @return A sequence containing declarations with the parent declaration of the specified type.
 */
inline fun <reified T> Sequence<KoParentDeclarationProvider>.withParentDeclarationOf(): Sequence<KoParentDeclarationProvider> =
    withAllParentDeclarationsOf(T::class)

/**
 * Sequence containing all declarations without named parent declarations.
 *
 * @return A sequence containing declarations without parent declaration of the specified type.
 */
inline fun <reified T> Sequence<KoParentDeclarationProvider>.withoutParentDeclarationOf(): Sequence<KoParentDeclarationProvider> =
    withoutAllParentDeclarationsOf(T::class)

/**
 * Sequence containing all declarations with named parent declarations.
 *
 * @param name The Kotlin class representing the parent declaration to include.
 * @param names The Kotlin declarations representing the parent declarations to include.
 * @return A sequence containing declarations with the parent declarations of the specified type(s).
 */
fun <T : KoParentDeclarationProvider> Sequence<T>.withAllParentDeclarationsOf(name: KClass<*>, vararg names: KClass<*>): Sequence<T> =
    filter {
        it.parentDeclarations.any { parent -> parent.name == name.simpleName } &&
                names.all { kClass ->
                    it
                        .parentDeclarations
                        .any { parent -> parent.name == kClass.simpleName }
                }
    }

/**
 * Sequence containing all declarations with some named parent declarations.
 *
 * @param name The Kotlin class representing the parent declaration to include.
 * @param names The Kotlin declarations representing the parent declarations to include.
 * @return A sequence containing declarations with at least one of the specified parent declaration(s).
 */
fun <T : KoParentDeclarationProvider> Sequence<T>.withSomeParentDeclarationsOf(name: KClass<*>, vararg names: KClass<*>): Sequence<T> =
    filter {
        it.parentDeclarations.any { parent -> parent.name == name.simpleName } ||
                names.any { kClass ->
                    it
                        .parentDeclarations
                        .any { parent -> parent.name == kClass.simpleName }
                }
    }

/**
 * Sequence containing all declarations without named parent declarations.
 *
 * @param name The Kotlin class representing the parent declaration to exclude.
 * @param names The Kotlin declarations representing the parent declarations to exclude.
 * @return A sequence containing declarations without parent declarations of the specified type(s).
 */
fun <T : KoParentDeclarationProvider> Sequence<T>.withoutAllParentDeclarationsOf(name: KClass<*>, vararg names: KClass<*>): Sequence<T> =
    filter {
        it.parentDeclarations.none { parent -> parent.name == name.simpleName } &&
                names.none { kClass ->
                    it
                        .parentDeclarations
                        .any { parent -> parent.name == kClass.simpleName }
                }
    }

/**
 * Sequence containing all declarations without some named parent declarations.
 *
 * @param name The Kotlin class representing the parent declaration to exclude.
 * @param names The Kotlin declarations representing the parent declarations to exclude.
 * @return A sequence containing declarations without at least one of the specified parent declaration(s).
 */
fun <T : KoParentDeclarationProvider> Sequence<T>.withoutSomeParentDeclarationsOf(name: KClass<*>, vararg names: KClass<*>): Sequence<T> =
    filter {
        it.parentDeclarations.none { parent -> parent.name == name.simpleName } &&
                if (names.isNotEmpty()) {
                    names.any { kClass ->
                        it
                            .parentDeclarations
                            .none { parent -> parent.name == kClass.simpleName }
                    }
                } else {
                    true
                }
    }
