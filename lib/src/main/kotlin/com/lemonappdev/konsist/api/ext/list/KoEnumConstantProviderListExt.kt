package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoEnumConstantDeclaration
import com.lemonappdev.konsist.api.provider.KoEnumConstantProvider

/**
 * List containing enum constant declarations.
 */
val <T : KoEnumConstantProvider> List<T>.enumConstants: List<KoEnumConstantDeclaration>
    get() = flatMap { it.enumConstants }

/**
 * List containing declarations with any enum constant.
 *
 * @return A list containing declarations with any enum constant.
 */
fun <T : KoEnumConstantProvider> List<T>.withEnumConstants(): List<T> = filter { it.hasEnumConstants() }

/**
 * List containing declarations whose all enum constants match the given predicate.
 *
 * @param predicate The predicate function to determine if a declaration enum constant satisfies a condition.
 * @return A list containing declarations with the specified enum constant(s).
 */
fun <T : KoEnumConstantProvider> List<T>.withAllEnumConstants(predicate: (KoEnumConstantDeclaration) -> Boolean): List<T> =
    filter { it.hasAllEnumConstants(predicate) }

/**
 * List containing declarations whose at least one enum constant matches the given predicate.
 *
 * @param predicate The predicate function to determine if a declaration enum constant satisfies a condition.
 * @return A list containing declarations with the specified enum constant(s).
 */
fun <T : KoEnumConstantProvider> List<T>.withEnumConstant(predicate: (KoEnumConstantDeclaration) -> Boolean): List<T> =
    filter { it.hasEnumConstant(predicate) }

/**
 * List containing declarations with all specified enum constants.
 *
 * @param name The enum constant name to include.
 * @param names The enum constant name(s) to include.
 * @return A list containing declarations with the specified enum constant(s).
 */
@Deprecated("Will be removed in v1.0.0", ReplaceWith("withAllEnumConstants { it.name == ... }"))
fun <T : KoEnumConstantProvider> List<T>.withAllEnumConstants(name: String, vararg names: String): List<T> = filter {
    it.hasEnumConstants(name, *names)
}

/**
 * List containing declarations with some enum constants.
 *
 * @param name The enum constant name to include.
 * @param names The enum constant name(s) to include.
 * @return A list containing declarations with at least one of the specified enum constant(s).
 */
@Deprecated("Will be removed in v1.0.0", ReplaceWith("withEnumConstant { it.name == ... }"))
fun <T : KoEnumConstantProvider> List<T>.withSomeEnumConstants(name: String, vararg names: String): List<T> = filter {
    it.hasEnumConstants(name) || names.any { constant -> it.hasEnumConstants(constant) }
}

/**
 * List containing declarations with no enum constant.
 *
 * @return A list containing declarations with no enum constant.
 */
fun <T : KoEnumConstantProvider> List<T>.withoutEnumConstants(): List<T> = filterNot { it.hasEnumConstants() }

/**
 * List containing declarations whose all enum constants do not match the predicate.
 *
 * @param predicate The predicate function to determine if a declaration enum constant satisfies a condition.
 * @return A list containing declarations without the specified enum constant(s).
 */
fun <T : KoEnumConstantProvider> List<T>.withoutAllEnumConstants(predicate: (KoEnumConstantDeclaration) -> Boolean): List<T> =
    filterNot { it.hasAllEnumConstants(predicate) }

/**
 * List containing declarations whose at least one enum constant does not match the predicate.
 *
 * @param predicate The predicate function to determine if a declaration enum constant satisfies a condition.
 * @return A list containing declarations with the specified enum constant(s).
 */
fun <T : KoEnumConstantProvider> List<T>.withoutEnumConstant(predicate: (KoEnumConstantDeclaration) -> Boolean): List<T> =
    filterNot { it.hasEnumConstant(predicate) }

/**
 * List containing declarations without all specified enum constants.
 *
 * @param name The enum constant name to exclude.
 * @param names The enum constant name(s) to exclude.
 * @return A list containing declarations without specified enum constant(s).
 */
@Deprecated("Will be removed in v1.0.0", ReplaceWith("withoutAllEnumConstants { it.name == ... }"))
fun <T : KoEnumConstantProvider> List<T>.withoutAllEnumConstants(name: String, vararg names: String): List<T> =
    filterNot { it.hasEnumConstants(name, *names) }

/**
 * List containing declarations without some enum constants.
 *
 * @param name The enum constant name to exclude.
 * @param names The enum constant name(s) to exclude.
 * @return A list containing declarations without at least one of the specified enum constant(s).
 */
@Deprecated("Will be removed in v1.0.0", ReplaceWith("withoutEnumConstant { it.name == ... }"))
fun <T : KoEnumConstantProvider> List<T>.withoutSomeEnumConstants(name: String, vararg names: String): List<T> =
    filter {
        !it.hasEnumConstants(name) && if (names.isNotEmpty()) {
            names.any { constant -> !it.hasEnumConstants(constant) }
        } else {
            true
        }
    }
