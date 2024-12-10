package com.lemonappdev.konsist.api.architecture

/**
 * Represents an architectural layer within a software system.
 *
 * An architectural layer is defined by a name and a package path pattern that determines which code belongs to it.
 * The package pattern supports wildcards using ".." to include all subpackages at that level.
 *
 * Usage:
 * ```kotlin
 * // Standard layer definitions (wildcards at the end)
 * val domainLayer = Layer("Domain", "com.example.domain..")
 * val presentationLayer = Layer("Presentation", "com.example.presentation..")
 *
 * // Wildcards layer definitions (wildcards at the start and at the end)
 * val specificDomainLayer = Layer("Domain", "..domain..")  // matches any path ending with "domain"
 * ```
 *
 * Package Pattern Rules:
 * 1. Basic Structure
 *    - Must end with ".." to indicate subpackage inclusion
 *    - Cannot be empty (just "..")
 *    - Can start with ".." for wildcard matching
 *
 * 2. Package Segments
 *    - Must start with a lowercase letter
 *    - Can contain: lowercase letters, numbers, underscores
 *
 * 3. Wildcard Usage
 *    - ".." is only allowed at start or end of pattern
 *    - No consecutive dots except for wildcard notation
 *
 * @property name Layer identifier (non-blank)
 * @property rootPackage Package pattern defining layer scope (must follow pattern rules)
 * @throws IllegalArgumentException If name is blank or rootPackage violates pattern rules
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

        // Remove the ending '..' for further validation
        val packageWithoutEndingDots = rootPackage.removeSuffix("..")

        // Empty package (just ..) is not valid
        if (packageWithoutEndingDots.isEmpty()) {
            throw IllegalArgumentException(
                "Invalid package definition for layer '$name'. " +
                    "Package name cannot be empty. Current definition: $rootPackage",
            )
        }

        // Special handling for packages starting with '..'
        val effectivePackage =
            if (packageWithoutEndingDots.startsWith("..")) {
                packageWithoutEndingDots.substring(2)
            } else {
                packageWithoutEndingDots
            }

        // Check for starting dot (but not ..)
        if (effectivePackage.startsWith(".") && !packageWithoutEndingDots.startsWith("..")) {
            throw IllegalArgumentException(
                "Invalid package definition for layer '$name'. " +
                    "Package cannot start with a dot. Current definition: $rootPackage",
            )
        }

        // Split and validate segments
        val segments =
            effectivePackage
                .split(".")
                .filter { it.isNotEmpty() }

        // Validate each segment
        segments.forEachIndexed { index, segment ->
            if (!segment.matches(REGEX_VALID_PACKAGE_SEGMENT)) {
                throw IllegalArgumentException(
                    "Invalid package definition for layer '$name'. " +
                        "Invalid package segment '$segment' at position ${index + 1}. " +
                        "Package segments must start with a lowercase letter and contain only " +
                        "letters, numbers, or underscores. Current definition: $rootPackage",
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
        private val REGEX_VALID_PACKAGE_SEGMENT = Regex("^[a-z][a-zA-Z0-9_]*$")
    }
}
