package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import com.lemonappdev.konsist.core.util.LocationUtil

/**
 * Represents a layer within an architecture.
 *
 * @param name The name of the layer.
 * @param rootPackage The root package that defines this layer. The layer contains all classes from this package and its
 * subpackages. The root package definition must end with two dots, e.g., `com.app.domain..`.
 *
 * @throws KoPreconditionFailedException if:
 * - The [rootPackage] starts with a single dot.
 * - The [rootPackage] must end with '..' (when including subpackages).
 * - The [rootPackage] contains more than two consecutive dots.
 */
data class Layer(
    internal val name: String,
    internal val rootPackage: String,
) {
    init {
        require(name.isNotBlank()) { "name is blank" }
        require(rootPackage.isNotBlank()) { "rootPackage is blank" }

        val pattern = Regex(pattern = LocationUtil.REGEX_PACKAGE_NAME_END_TWO_DOTS)
        val twoDotsAtTheEndPattern = Regex(pattern = LocationUtil.REGEX_PACKAGE_NAME_END_TWO_DOTS)

        val withoutSingleDotAtTheBeginningPattern =
            Regex(pattern = LocationUtil.REGEX_PACKAGE_NAME_WITHOUT_SINGLE_DOT_AT_THE_BEGINNING)

        val withoutFewDotsInOnePlacePattern =
            Regex(pattern = LocationUtil.REGEX_PACKAGE_NAME_WITHOUT_FEW_DOTS_IN_ONE_PLACE)

        if (!rootPackage.matches(pattern)) {
            if (!rootPackage.matches(withoutSingleDotAtTheBeginningPattern)) {
                throw KoPreconditionFailedException(
                    "Invalid package definition for layer '$name'. " +
                        "Package names cannot start with a single dot. Current definition: $rootPackage",
                )
            }

            if (!rootPackage.matches(twoDotsAtTheEndPattern)) {
                throw KoPreconditionFailedException(
                    "Invalid package definition for layer '$name'. " +
                        "To include subpackages, the definition must end with '..'. Current definition: $rootPackage",
                )
            }

            if (!rootPackage.matches(withoutFewDotsInOnePlacePattern)) {
                throw KoPreconditionFailedException(
                    "Invalid package definition for layer '$name'. " +
                        "Package names cannot contain more than two consecutive dots. Current definition: $rootPackage",
                )
            }
        }
    }
}
