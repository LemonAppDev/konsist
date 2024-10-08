package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import com.lemonappdev.konsist.core.util.LocationUtil

/**
 * Represents a layer within an architecture.
 *
 * @param name The name of the layer.
 * @param definedBy The package that defines this layer. Layer contains all classes from this package and its subpackages.
 * Layer definition must end with two dots, e.g., `com.app.domain..`.
 *
 * @throws KoPreconditionFailedException if:
 * - The [definedBy] package starts with a single dot.
 * - The [definedBy] package doesn't end with '..' (when including subpackages).
 * - The [definedBy] package contains more than two consecutive dots.
 */
data class Layer(
    internal val name: String,
    internal val definedBy: String,
) {
    init {
        val pattern = Regex(pattern = LocationUtil.REGEX_PACKAGE_NAME_END_TWO_DOTS)
        val twoDotsAtTheEndPattern = Regex(pattern = LocationUtil.REGEX_PACKAGE_NAME_END_TWO_DOTS)

        val withoutSingleDotAtTheBeginningPattern =
            Regex(pattern = LocationUtil.REGEX_PACKAGE_NAME_WITHOUT_SINGLE_DOT_AT_THE_BEGINNING)

        val withoutFewDotsInOnePlacePattern =
            Regex(pattern = LocationUtil.REGEX_PACKAGE_NAME_WITHOUT_FEW_DOTS_IN_ONE_PLACE)

        if (!definedBy.matches(pattern)) {
            if (!definedBy.matches(withoutSingleDotAtTheBeginningPattern)) {
                throw KoPreconditionFailedException(
                    "Invalid package definition for layer '$name'. " +
                        "Package names cannot start with a single dot. Current definition: $definedBy",
                )
            }

            if (!definedBy.matches(twoDotsAtTheEndPattern)) {
                throw KoPreconditionFailedException(
                    "Invalid package definition for layer '$name'. " +
                        "To include subpackages, the definition must end with '..'. Current definition: $definedBy",
                )
            }

            if (!definedBy.matches(withoutFewDotsInOnePlacePattern)) {
                throw KoPreconditionFailedException(
                    "Invalid package definition for layer '$name'. " +
                        "Package names cannot contain more than two consecutive dots. Current definition: $definedBy",
                )
            }
        }
    }
}
