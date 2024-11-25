package com.lemonappdev.konsist.api.architecture

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
 */
@Suppress("detekt.ThrowsCount")
data class Layer(
    internal val name: String,
    internal val rootPackage: String,
) {
    init {
        require(name.isNotBlank()) { "name is blank" }
        require(rootPackage.isNotBlank()) { "rootPackage is blank" }
        validatePackageDefinition()
    }

    private fun validatePackageDefinition(): Unit {
        if (rootPackage == "..") {
            throw IllegalArgumentException(
                "Invalid rootPackage definition for layer '$name'. " +
                    "Package name cannot be empty. Current definition: $rootPackage",
            )
        }

        // Check if ends with exactly '..'
        if (!endsWithExactlyTwoDots()) {
            throw IllegalArgumentException(
                buildPackageErrorMessage(),
            )
        }

        // Check if there's more than one ".." in the package
        if (countConsecutiveDoubleDots(rootPackage) > 1) {
            throw IllegalArgumentException(
                "Invalid package definition for layer '$name'. Package can only end with '..'. " +
                    "Current definition: $rootPackage",
            )
        }

        val packageWithoutDoubleDot = rootPackage.removeSuffix("..")

        // Empty package (just ..) is not valid
        if (packageWithoutDoubleDot.isEmpty()) {
            throw IllegalArgumentException(
                "Invalid package definition for layer '$name'. " +
                    "Package name cannot be empty. Current definition: $rootPackage",
            )
        }

        // Check for starting dot
        if (packageWithoutDoubleDot.startsWith(".")) {
            throw IllegalArgumentException(
                "Invalid package definition for layer '$name'. " +
                    "Package cannot start with a dot. Current definition: $rootPackage",
            )
        }

        // Validate each package segment
        val segments =
            packageWithoutDoubleDot
                .split(".")
                .filter { it.isNotEmpty() && it != "." } // Filter out empty segments and single dots
                .map { if (it == ".") "" else it } // Handle any remaining dots

        segments.forEachIndexed { index, segment ->
            if (!segment.matches(REGEX_VALID_PACKAGE_SEGMENT)) {
                throw IllegalArgumentException(
                    "Invalid package definition for layer '$name'. " +
                        "Invalid package segment '$segment' at position ${index + 1}. " +
                        "Package segments must start with a lowercase letter and contain only " +
                        "lowercase letters, numbers, or underscores. Current definition: $rootPackage",
                )
            }
        }
    }

    /**
     * Counts the number of ".." occurrences in the string.
     * Looks for exactly two consecutive dots, not counting sequences of 3 or more dots.
     *
     * @param str The string to check
     * @return The number of ".." occurrences
     */
    private fun countConsecutiveDoubleDots(str: String): Int {
        var count = 0
        var i = 0
        while (i < str.length - 1) {
            if (str[i] == '.' && str[i + 1] == '.') {
                // Check if it's exactly two dots (not three or more)
                if (i + 2 >= str.length || str[i + 2] != '.') {
                    count++
                }
                i += 2
            } else {
                i++
            }
        }
        return count
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
