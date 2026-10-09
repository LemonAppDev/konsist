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
 * List containing declarations with no enum constant.
 *
 * @return A list containing declarations with no enum constant.
 */
fun <T : KoEnumConstantProvider> List<T>.withoutEnumConstants(): List<T> = filterNot { it.hasEnumConstants() }

/**
 * List containing declarations that have at least one enum constant with the specified name(s).
 *
 * @param name The name of the enum constant to include.
 * @param names The names of additional enum constants to include.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with at least one of the specified enum constant(s).
 */
fun <T : KoEnumConstantProvider> List<T>.withEnumConstantNamed(
    name: String,
    vararg names: String,
    ignoreCase: Boolean = false,
): List<T> = withEnumConstantNamed(listOf(name, *names), ignoreCase)

/**
 * List containing declarations that have at least one enum constant with the specified name(s).
 *
 * @param names The names of additional enum constants to include.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with at least one of the specified enum constant(s).
 */
fun <T : KoEnumConstantProvider> List<T>.withEnumConstantNamed(
    names: Collection<String>,
    ignoreCase: Boolean = false,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasEnumConstants()
            else -> it.hasEnumConstantWithName(names, ignoreCase = ignoreCase)
        }
    }

/**
 * List containing declarations without any of specified enum constants.
 *
 * @param name The name of the enum constant to exclude.
 * @param names The names of additional enum constants to exclude.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without any of specified enum constants.
 */
fun <T : KoEnumConstantProvider> List<T>.withoutEnumConstantNamed(
    name: String,
    vararg names: String,
    ignoreCase: Boolean = false,
): List<T> = withoutEnumConstantNamed(listOf(name, *names), ignoreCase)

/**
 * List containing declarations without any of specified enum constants.
 *
 * @param names The names of additional enum constants to exclude.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without any of specified enum constants.
 */
fun <T : KoEnumConstantProvider> List<T>.withoutEnumConstantNamed(
    names: Collection<String>,
    ignoreCase: Boolean = false,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasEnumConstants()
            else -> it.hasEnumConstantWithName(names, ignoreCase = ignoreCase)
        }
    }

/**
 * List containing declarations that have all specified enum constants.
 *
 * @param name The name of the enum constant to include.
 * @param names The name(s) of the enum constant(s) to include.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with all specified enum constant(s).
 */
fun <T : KoEnumConstantProvider> List<T>.withAllEnumConstantsNamed(
    name: String,
    vararg names: String,
    ignoreCase: Boolean = false,
): List<T> = withAllEnumConstantsNamed(listOf(name, *names), ignoreCase)

/**
 * List containing declarations that have all specified enum constants.
 *
 * @param names The name(s) of the enum constant(s) to include.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with all specified enum constant(s).
 */
fun <T : KoEnumConstantProvider> List<T>.withAllEnumConstantsNamed(
    names: Collection<String>,
    ignoreCase: Boolean = false,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasEnumConstants()
            else -> it.hasEnumConstantsWithAllNames(names, ignoreCase = ignoreCase)
        }
    }

/**
 * List containing declarations without all specified enum constants.
 *
 * @param name The name of the enum constant to exclude.
 * @param names The name(s) of the enum constant(s) to exclude.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without all specified enum constant(s).
 */
fun <T : KoEnumConstantProvider> List<T>.withoutAllEnumConstantsNamed(
    name: String,
    vararg names: String,
    ignoreCase: Boolean = false,
): List<T> = withoutAllEnumConstantsNamed(listOf(name, *names), ignoreCase)

/**
 * List containing declarations without all specified enum constants.
 *
 * @param names The name(s) of the enum constant(s) to exclude.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without all specified enum constant(s).
 */
fun <T : KoEnumConstantProvider> List<T>.withoutAllEnumConstantsNamed(
    names: Collection<String>,
    ignoreCase: Boolean = false,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasEnumConstants()
            else -> it.hasEnumConstantsWithAllNames(names, ignoreCase = ignoreCase)
        }
    }

/**
 * List containing declarations that have at least one enum constant satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by an enum constant declaration.
 * @return A list containing declarations with at least one enum constant satisfying the predicate.
 */
fun <T : KoEnumConstantProvider> List<T>.withEnumConstant(predicate: (KoEnumConstantDeclaration) -> Boolean): List<T> =
    filter { it.hasEnumConstant(predicate) }

/**
 * List containing declarations that not have enum constant satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by an enum constant declaration.
 * @return A list containing declarations without enum constant satisfying the provided predicate.
 */
fun <T : KoEnumConstantProvider> List<T>.withoutEnumConstant(predicate: (KoEnumConstantDeclaration) -> Boolean): List<T> =
    filterNot { it.hasEnumConstant(predicate) }

/**
 * List containing declarations that have all enum constants satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all enum constant declarations.
 * @return A filtered list containing declarations with all enum constants satisfying the predicate.
 */
fun <T : KoEnumConstantProvider> List<T>.withAllEnumConstants(predicate: (KoEnumConstantDeclaration) -> Boolean): List<T> =
    filter { it.hasAllEnumConstants(predicate) }

/**
 * List containing declarations that have at least one enum constant not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all enum constant declarations.
 * @return A list containing declarations that have at least one enum constant not satisfying the provided predicate.
 */
fun <T : KoEnumConstantProvider> List<T>.withoutAllEnumConstants(predicate: (KoEnumConstantDeclaration) -> Boolean): List<T> =
    filterNot { it.hasAllEnumConstants(predicate) }

/**
 * List containing declarations with enum constant declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of enum constant declarations.
 * @return A list containing declarations with enum constant declarations satisfying the predicate.
 */
fun <T : KoEnumConstantProvider> List<T>.withEnumConstants(predicate: (List<KoEnumConstantDeclaration>) -> Boolean): List<T> =
    filter { predicate(it.enumConstants) }

/**
 * List containing declarations without enum constant declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of enum constant declarations.
 * @return A list containing declarations without enum constant declarations satisfying the predicate.
 */
fun <T : KoEnumConstantProvider> List<T>.withoutEnumConstants(predicate: (List<KoEnumConstantDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.enumConstants) }
