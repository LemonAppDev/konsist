package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import com.lemonappdev.konsist.core.util.LocationUtil

/**
 * Represents a layer within an architecture.
 *
 * @param name The name of the layer.
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

        if (!definedBy.matches(pattern)) {
            throw KoPreconditionFailedException("Layer $name must be defined by package ending with '..'. Now: $definedBy .")
        }
    }
}
