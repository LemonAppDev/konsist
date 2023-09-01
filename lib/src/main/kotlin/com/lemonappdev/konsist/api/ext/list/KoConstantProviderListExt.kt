package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoConstantDeclaration
import com.lemonappdev.konsist.api.provider.KoConstantProvider

/**
 * List containing enum constant declarations.
 */
val <T : KoConstantProvider> List<T>.constants: List<KoConstantDeclaration>
    get() = flatMap { it.constants }

/**
 * List containing elements with any enum constant.
 *
 * @return A list containing elements with any enum constant.
 */
fun <T : KoConstantProvider> List<T>.withConstants(): List<T> = filter { it.hasConstants() }

/**
 * List containing elements with all specified enum constants.
 *
 * @param name The enum constant name to include.
 * @param names The enum constant name(s) to include.
 * @return A list containing elements with the specified enum constant(s).
 */
fun <T : KoConstantProvider> List<T>.withAllConstants(name: String, vararg names: String): List<T> = filter {
    it.hasConstants(name, *names)
}

/**
 * List containing elements with some enum constants.
 *
 * @param name The enum constant name to include.
 * @param names The enum constant name(s) to include.
 * @return A list containing elements with at least one of the specified enum constant(s).
 */
fun <T : KoConstantProvider> List<T>.withSomeConstants(name: String, vararg names: String): List<T> = filter {
    it.hasConstants(name) || names.any { constant -> it.hasConstants(constant) }
}

/**
 * List containing elements with no enum constant.
 *
 * @return A list containing elements with no enum constant.
 */
fun <T : KoConstantProvider> List<T>.withoutConstants(): List<T> = filterNot { it.hasConstants() }

/**
 * List containing elements without all specified enum constants.
 *
 * @param name The enum constant name to exclude.
 * @param names The enum constant name(s) to exclude.
 * @return A list containing elements without specified enum constant(s).
 */
fun <T : KoConstantProvider> List<T>.withoutAllConstants(name: String, vararg names: String): List<T> = filterNot {
    it.hasConstants(name, *names)
}

/**
 * List containing elements without some enum constants.
 *
 * @param name The enum constant name to exclude.
 * @param names The enum constant name(s) to exclude.
 * @return A list containing elements without at least one of the specified enum constant(s).
 */
fun <T : KoConstantProvider> List<T>.withoutSomeConstants(name: String, vararg names: String): List<T> = filter {
    !it.hasConstants(name) && if (names.isNotEmpty()) {
        names.any { constant -> !it.hasConstants(constant) }
    } else {
        true
    }
}
