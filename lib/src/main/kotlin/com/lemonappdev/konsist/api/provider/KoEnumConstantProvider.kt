package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoEnumConstantDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to enum constants.
 *
 */
interface KoEnumConstantProvider : KoBaseProvider {
    /**
     * List of enum constants.
     */
    val enumConstants: List<KoEnumConstantDeclaration>

    /**
     * The number of enum constants.
     */
    val numEnumConstants: Int

    /**
     * Gets the number of enum constants that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if an enum constant satisfies a condition.
     * @return The number of enum constants in the declaration.
     */
    fun countEnumConstants(predicate: (KoEnumConstantDeclaration) -> Boolean): Int

    /**
     * Whether the declaration has enum constants.
     *
     * @param names the names of the enum constants to check.
     * @return `true` if the declaration has enum constants with the specified names (or any constant if [names] is empty),
     * `false` otherwise.
     */
    @Deprecated(
        "Will be removed in v1.0.0",
        ReplaceWith("hasEnumConstant { it.name == ... } && hasEnumConstant { it.name == ... } ..."),
    )
    fun hasEnumConstants(vararg names: String): Boolean

    /**
     * Whether the declaration has any enum constant.
     *
     * @return `true` if the declaration has any enum constant, `false` otherwise.
     */
    fun hasEnumConstants(): Boolean

    /**
     * Whether the declaration has any enum constant with the specified predicate.
     *
     * @param predicate The predicate function to determine if an enum constant satisfies a condition.
     * @return `true` if the declaration has enum constants with the specified predicate, `false` otherwise.
     */
    fun hasEnumConstant(predicate: (KoEnumConstantDeclaration) -> Boolean): Boolean

    /**
     * Whether the declaration has all enum constants with the specified predicate.
     *
     * Note that if the enum constants contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate. See a more detailed explanation of this logic concept in
     * ["Vacuous truth"](https://en.wikipedia.org/wiki/Vacuous_truth) article.
     *
     * @param predicate The predicate function to determine if an enum constant satisfies a condition.
     * @return `true` if the declaration has all enum constants with the specified predicate, `false` otherwise.
     */
    fun hasAllEnumConstants(predicate: (KoEnumConstantDeclaration) -> Boolean): Boolean
}
