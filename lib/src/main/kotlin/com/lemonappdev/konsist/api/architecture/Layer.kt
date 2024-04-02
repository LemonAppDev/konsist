package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import com.lemonappdev.konsist.core.util.LocationUtil

/**
 * Represents a layer within an architecture.
 *
 * @param name The name of the layer.
 * @param definedBy The package or module that defines the layer. It should end with '..'.
 * @param definedBy The package that defines the layer.
 * To include classes in a give package the package and all the subpackages pass two dots at the end e.g.
 * `com.app.domain..`. To include classes only from a package pass the package name e.g. `com.app.domain`.
 *
 * The double dots at the end signify that the layer corresponds to the com.myapp.business package, encompassing
 * all of its sub-packages.
 *
 * @throws KoPreconditionFailedException if the [definedBy] package does not end with '..'.
 */
data class Layer(internal val name: String, internal val definedBy: String) {
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
                    "Layer $name cannot be defined by a package starting with a single dot. Now: $definedBy .",
                )
            }

            if (!definedBy.matches(twoDotsAtTheEndPattern)) {
                throw KoPreconditionFailedException(
                    "Layer $name must be defined by package ending with '..'. Now: $definedBy .",
                )
            }

            if (!definedBy.matches(withoutFewDotsInOnePlacePattern)) {
                throw KoPreconditionFailedException(
                    "Layer $name cannot be defined by a package containing more than two dots in one place. Now: $definedBy .",
                )
            }
        }
    }
}
