package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

/**
 * Represents an architectural layer within a software system.
 *
 * A layer is defined by its name and a root package path that determines which code belongs to this layer.
 * The root package path supports wildcards using ".." notation to include all subpackages.
 *
 * Example usage:
 * ```
 * val domainLayer = Layer("Domain", "com.example.domain..")
 * val presentationLayer = Layer("Presentation", "com.example.presentation..")
 * ```
 *
 * Package naming rules:
 * - Must end with ".." to indicate inclusion of all subpackages
 * - Cannot be empty (just "..")
 * - Cannot start with a dot
 * - Each package segment must:
 *   - Start with a lowercase letter
 *   - Contain only lowercase letters, numbers, or underscores
 *   - Follow standard Java package naming conventions
 * - Intermediate ".." wildcards are allowed (e.g., "com..domain..")
 *
 * @property name The name of the layer. Must not be blank.
 * @property rootPackage The package path defining this layer. Must follow the package naming rules.
 * @throws IllegalArgumentException If name or rootPackage is blank
 * @throws KoPreconditionFailedException If the rootPackage doesn't follow the package naming rules
 */
data class Layer(
    internal val name: String,
    internal val rootPackage: String,
) {
    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("name is blank")
        }
        if (rootPackage.isBlank()) {
            throw IllegalArgumentException("rootPackage is blank")
        }

        validatePackageDefinition()
    }

    private fun validatePackageDefinition() {
        // Check if ends with exactly '..'
        if (!endsWithExactlyTwoDots()) {
            throw KoPreconditionFailedException(
                buildPackageErrorMessage()
            )
        }

        val packageWithoutDoubleDot = rootPackage.removeSuffix("..")

        // Empty package (just ..) is not valid
        if (packageWithoutDoubleDot.isEmpty()) {
            throw KoPreconditionFailedException(
                "Invalid package definition for layer '$name'. " +
                        "Package name cannot be empty. Current definition: $rootPackage"
            )
        }

        // Check for starting dot
        if (packageWithoutDoubleDot.startsWith(".")) {
            throw KoPreconditionFailedException(
                "Invalid package definition for layer '$name'. " +
                        "Package cannot start with a dot. Current definition: $rootPackage"
            )
        }

        // Validate each package segment
        val segments = packageWithoutDoubleDot
            .split(".")
            .filter { it.isNotEmpty() && it != "." } // Filter out empty segments and single dots
            .map { if (it == ".") "" else it } // Handle any remaining dots

        segments.forEachIndexed { index, segment ->
            if (!segment.matches(REGEX_VALID_PACKAGE_SEGMENT)) {
                throw KoPreconditionFailedException(
                    "Invalid package definition for layer '$name'. " +
                            "Invalid package segment '$segment' at position ${index + 1}. " +
                            "Package segments must start with a lowercase letter and contain only " +
                            "lowercase letters, numbers, or underscores. Current definition: $rootPackage"
                )
            }
        }
    }

    private fun endsWithExactlyTwoDots(): Boolean {
        val lastIndex = rootPackage.length - 1
        return lastIndex >= 1 &&
                rootPackage[lastIndex] == '.' &&
                rootPackage[lastIndex - 1] == '.' &&
                (lastIndex < 2 || rootPackage[lastIndex - 2] != '.')
    }

    private fun buildPackageErrorMessage(): String =
        "Invalid package definition for layer '$name'. To include subpackages, " +
                "the definition must end with '..'. Current definition: $rootPackage"

    private companion object {
        private val REGEX_VALID_PACKAGE_SEGMENT = Regex("^[a-z][a-z0-9_]*$")
    }
}
